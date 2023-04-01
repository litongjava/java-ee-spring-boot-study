package top.ppnt.study.spring.boot.elasticsearch.query;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

/**
 * java操作查询api
 * @author 231
 *
 */
public class JavaESQuery {

  private TransportClient client;

  @Before
  public void testBefore() {
    String hostname = "127.0.0.1";
    int port = 9300;
    // transportAddress
    TransportAddress transportAddress = new TransportAddress(new InetSocketAddress(hostname, port));
    Builder builder = Settings.builder();
    Settings settings = builder.put("cluster.name", "wenbronk_escluster").build();

    client = new PreBuiltTransportClient(settings);
    client.addTransportAddress(transportAddress);
    System.out.println("success to connect escluster");
  }

  /**
   * 使用get查询
   */
  @Test
  public void testGet() {
    GetRequestBuilder requestBuilder = client.prepareGet("twitter", "tweet", "1");
    GetResponse response = requestBuilder.execute().actionGet();
    GetResponse getResponse = requestBuilder.get();
    ActionFuture<GetResponse> execute = requestBuilder.execute();
    System.out.println(response.getSourceAsString());
  }

  /**
   * 使用QueryBuilder
   * termQuery("key", obj) 完全匹配
   * termsQuery("key", obj1, obj2..)   一次匹配多个值
   * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
   * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
   * matchAllQuery();         匹配所有文件
   */
  @Test
  public void testQueryBuilder() {
    // QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "kimchy");
    // QueryBUilder queryBuilder = QueryBuilders.termQuery("user", "kimchy",
    // "wenbronk", "vini");
    QueryBuilders.termsQuery("user", new ArrayList<String>().add("kimchy"));
    // QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
    // QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("kimchy",
    // "user", "message", "gender");
    QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
    searchFunction(queryBuilder);

  }

  /**
   * 组合查询
   * must(QueryBuilders) :   AND
   * mustNot(QueryBuilders): NOT
   * should:                  : OR
   */
  @Test
  public void testQueryBuilder2() {
    QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("user", "kimchy"))
        .mustNot(QueryBuilders.termQuery("message", "nihao")).should(QueryBuilders.termQuery("gender", "male"));
    searchFunction(queryBuilder);
  }

  /**
   * 只查询一个id的
   * QueryBuilders.idsQuery(String...type).ids(Collection<String> ids)
   */
  @Test
  public void testIdsQuery() {
    IdsQueryBuilder idsQuery = QueryBuilders.idsQuery();
    QueryBuilder queryBuilder = idsQuery.addIds("1");
    searchFunction(queryBuilder);
  }

  /**
   * 包裹查询, 高于设定分数, 不计算相关性
   */
  @Test
  public void testConstantScoreQuery() {
    QueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("name", "kimchy")).boost(2.0f);
    searchFunction(queryBuilder);
    // 过滤查询
    // QueryBuilders.constantScoreQuery(FilterBuilders.termQuery("name",
    // "kimchy")).boost(2.0f);

  }

  /**
   * disMax查询
   * 对子查询的结果做union, score沿用子查询score的最大值, 
   * 广泛用于muti-field查询
   */
  @Test
  public void testDisMaxQuery() {
    QueryBuilder queryBuilder = QueryBuilders.disMaxQuery().add(QueryBuilders.termQuery("user", "kimch")) // 查询条件
        .add(QueryBuilders.termQuery("message", "hello")).boost(1.3f).tieBreaker(0.7f);
    searchFunction(queryBuilder);
  }

  /**
   * 模糊查询
   * 不能用通配符, 不知道干啥用
   */
  @Test
  public void testFuzzyQuery() {
    QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("user", "kimch");
    searchFunction(queryBuilder);
  }

  /**
   * moreLikeThisQuery: 实现基于内容推荐, 支持实现一句话相似文章查询
   * {   
      "more_like_this" : {   
      "fields" : ["title", "content"],   // 要匹配的字段, 不填默认_all
      "like_text" : "text like this one",   // 匹配的文本
      }   
  }     
  
  percent_terms_to_match：匹配项（term）的百分比，默认是0.3
  
  min_term_freq：一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2
  
  max_query_terms：一条查询语句中允许最多查询词语的个数，默认是25
  
  stop_words：设置停止词，匹配时会忽略停止词
  
  min_doc_freq：一个词语最少在多少篇文档中出现，小于这个值的词会将被忽略，默认是无限制
  
  max_doc_freq：一个词语最多在多少篇文档中出现，大于这个值的词会将被忽略，默认是无限制
  
  min_word_len：最小的词语长度，默认是0
  
  max_word_len：最多的词语长度，默认无限制
  
  boost_terms：设置词语权重，默认是1
  
  boost：设置查询权重，默认是1
  
  analyzer：设置使用的分词器，默认是使用该字段指定的分词器
   */
  // @Test
  // public void testMoreLikeThisQuery() {
  // String[] fields= {"user"};
  // String[] likeTexts= {"kimchy"};
  // QueryBuilder queryBuilder =
  // QueryBuilders.moreLikeThisQuery(fields,likeTexts);
  // // .minTermFreq(1) //最少出现的次数
  // // .maxQueryTerms(12); // 最多允许查询的词语
  // searchFunction(queryBuilder);
  // }

  /**
   * 前缀查询
   */
  @Test
  public void testPrefixQuery() {
    QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
    searchFunction(queryBuilder);
  }

  /**
   * 查询解析查询字符串
   */
  @Test
  public void testQueryString() {
    QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("+kimchy");
    searchFunction(queryBuilder);
  }

  /**
   * 范围内查询
   */
  public void testRangeQuery() {
    QueryBuilder queryBuilder = QueryBuilders.rangeQuery("user").from("kimchy").to("wenbronk").includeLower(true) // 包含上界
        .includeUpper(true); // 包含下届
    searchFunction(queryBuilder);
  }

  /**
   * 跨度查询
   */

  /**
   * 通配符查询, 支持 * 
   * 匹配任何字符序列, 包括空
   * 避免* 开始, 会检索大量内容造成效率缓慢
   */
  @Test
  public void testWildCardQuery() {
    QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("user", "ki*hy");
    searchFunction(queryBuilder);
  }

  /**
   * 嵌套查询, 内嵌文档查询
   */
  @Test
  public void testNestedQuery() {
    String path = "location";
    QueryBuilder query = QueryBuilders.boolQuery();
    org.apache.lucene.search.join.ScoreMode scoreMode = org.apache.lucene.search.join.ScoreMode.Total;
    QueryBuilder queryBuilder = QueryBuilders.nestedQuery(path, query, scoreMode);
  }

  /**
   * 查询遍历抽取
   * @param queryBuilder
   */
  private void searchFunction(QueryBuilder queryBuilder) {
    SearchRequestBuilder searchRequestBuilder = client.prepareSearch("twitter");
    searchRequestBuilder.setSearchType(
        //
        SearchType.DFS_QUERY_THEN_FETCH)
        //
        .setScroll(new TimeValue(60000))
        //
        .setQuery(queryBuilder).setSize(100);

    SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

    while (true) {
      searchResponse = client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
      for (SearchHit hit : searchResponse.getHits()) {
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        Iterator<Entry<String, Object>> iterator = sourceAsMap.entrySet().iterator();
        while (iterator.hasNext()) {
          Entry<String, Object> next = iterator.next();
          System.out.println(next.getKey() + ": " + next.getValue());
          if (searchResponse.getHits().getHits().length == 0) {
            break;
          }
        }
      }
      break;
    }
    // testResponse(response);
  }

  /**
   * 对response结果的分析
   * @param response
   */
  public void testResponse(SearchResponse response) {
    SearchHits searchHits = response.getHits();
    // 命中的记录数
    TotalHits totalHits = searchHits.getTotalHits();

    for (SearchHit searchHit : response.getHits()) {
      Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();

      // 打分
      float score = searchHit.getScore();
      // 文章id
      int id = Integer.parseInt(searchHit.getId().toString());
      // title
      String title = sourceAsMap.get("title").toString();
      // 内容
      String content = sourceAsMap.get("content").toString();
      // 文章更新时间
      long updatetime = Long.parseLong(sourceAsMap.get("updatetime").toString());
    }
  }
}
