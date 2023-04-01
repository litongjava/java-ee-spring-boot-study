package top.ppnt.study.spring.boot.elasticsearch.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.alibaba.fastjson.JSON;

/**
 * @author Ping E Lee
 *
 */
@SpringBootTest
public class UserTest {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  // 索引名称
  String indexName = "user";

  /**
   * 添加文档 PUT zoomy_index/_doc/1
   * @throws IOException
   */
  @Test
  public void addDocument() throws IOException {
    // 保存数据
    User user = new User("zoomy", 21);
    String jsonString = JSON.toJSONString(user);
    // 请求数据
    IndexRequest request = new IndexRequest(indexName);
    request.id("1");
    request.timeout(TimeValue.timeValueSeconds(1));
    request.source(jsonString, XContentType.JSON);
    // 客户端发送请求,获取响应结果
    IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    System.out.println(indexResponse.toString());
    // 命令返回的状态
    System.out.println(indexResponse.status());
  }

  /**
   * 获取文档，判断是否存在
   * @throws IOException
   */
  @Test
  void exitDocument() throws IOException {
    GetRequest request = new GetRequest(indexName, "1");
    // 不获取返回的_source的上下文，效率更高
    request.fetchSourceContext(new FetchSourceContext(false));
    request.storedFields("_none_");
    // exists
    boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);
  }

  /**
   * 获取文档信息
   * @throws IOException
   */
  @Test
  void getDocument() throws IOException {
    GetRequest request = new GetRequest(indexName, "1");
    GetResponse getResponse = restHighLevelClient.get(request, RequestOptions.DEFAULT);
    // 打印文档内容
    System.out.println(getResponse.getSourceAsString());
    // 返回全部内容
    System.out.println(getResponse);

  }

  /**
   * 更新文档 POST zoomy_index/_doc/1/_update
   * @throws IOException
   */
  @Test
  void updateDocument() throws IOException {
    // data
    User user = new User("zoomy", 22);
    String jsonString = JSON.toJSONString(user);
    // request
    UpdateRequest request = new UpdateRequest(indexName, "1");
    request.timeout(TimeValue.timeValueSeconds(1));
    request.doc(jsonString, XContentType.JSON);
    // update
    UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
    System.out.println(updateResponse.status());
  }

  /**
   * 删除文档
   * @throws IOException
   */
  @Test
  void deleteDocument() throws IOException {
    // request
    DeleteRequest request = new DeleteRequest(indexName, "1");
    // delete
    DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
    System.out.println(deleteResponse.status());
  }

  // 批量添加数据
  @Test
  void bulkRequest() throws IOException {
    // data
    ArrayList<User> userList = new ArrayList<>();
    userList.add(new User("zoomy1", 21));
    userList.add(new User("zoomy2", 22));
    userList.add(new User("zoomy3", 23));

    // request
    BulkRequest bulkRequest = new BulkRequest();
    bulkRequest.timeout(TimeValue.timeValueSeconds(10));
    for (int i = 0; i < userList.size(); i++) {
      IndexRequest request = new IndexRequest(indexName);
      String jsonString = JSON.toJSONString(userList.get(i));
      request.id("" + (i + 1)).source(jsonString, XContentType.JSON);
      bulkRequest.add(request);
    }
    // bulk
    BulkResponse bulkItemResponses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    System.out.println(bulkItemResponses.hasFailures());
  }

  /**
   * 搜索数据,指定搜索字段
   * @throws IOException
   */
  @Test
  void searchRequest() throws IOException {
    // 构建搜索条件
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    // 查询条件QueryBuilders工具 termQuery 精确查询
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "zoom");
    // 添加查询条件
    searchSourceBuilder.query(termQueryBuilder);
    // 设置超时时间
    searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    // searchReequest
    SearchRequest searchRequest = new SearchRequest(indexName);
    searchRequest.source(searchSourceBuilder);
    // 执行搜索
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // getHits
    SearchHits hits = searchResponse.getHits();
    // println
    System.out.println(JSON.toJSONString(hits));

    for (SearchHit hit : hits.getHits()) {
      Map<String, Object> sourceAsMap = hit.getSourceAsMap();
      System.out.println(sourceAsMap);
    }
  }

  /**
   * 搜索所有数据
   * @throws IOException 
   */
  @Test
  public void searchAll() throws IOException {
    // 构建搜索条件
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    // 查询条件QueryBuilders工具 matchAllQuery匹配所有
    MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
    // 添加查询条件
    searchSourceBuilder.query(matchAllQueryBuilder);
    // from size有默认参数
    searchSourceBuilder.from();
    searchSourceBuilder.size();
    // 设置超时时间
    searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    // searchReequest
    SearchRequest searchRequest = new SearchRequest(indexName);
    searchRequest.source(searchSourceBuilder);
    // 执行搜索
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // getHits
    SearchHits hits = searchResponse.getHits();
    // println
    System.out.println(JSON.toJSONString(hits));

    for (SearchHit hit : hits.getHits()) {
      Map<String, Object> sourceAsMap = hit.getSourceAsMap();
      System.out.println(sourceAsMap);
    }
  }

  @Test
  void searchQueryRequest() throws IOException {
    // 构建搜索条件
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    // 查询条件QueryBuilders工具 termQuery 精确查询
    // TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name",
    // "zoom");
    // 没有匹配到数据
    // MultiMatchQueryBuilder queryBuilder =
    // QueryBuilders.multiMatchQuery("zoom", "name");
    MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("name", "?" + "zomm" + "?");
    // 查询到数据
    // MatchPhrasePrefixQueryBuilder queryBuilder =
    // QueryBuilders.matchPhrasePrefixQuery("name", "oom");
    // queryStringQueryBuilder
    // QueryStringQueryBuilder queryStringQueryBuilder = new
    // QueryStringQueryBuilder(keyword);
    //
    // // nativeSearchQueryBuilder
    // NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    //
    // // withQuery
    // queryBuilder.withQuery(queryStringQueryBuilder);

    // 添加查询条件
    searchSourceBuilder.query(queryBuilder);
    // 设置超时时间
    searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    // searchReequest
    SearchRequest searchRequest = new SearchRequest(indexName);
    searchRequest.source(searchSourceBuilder);
    // 执行搜索
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // getHits
    SearchHits hits = searchResponse.getHits();
    // println
    System.out.println(JSON.toJSONString(hits));

    for (SearchHit hit : hits.getHits()) {
      Map<String, Object> sourceAsMap = hit.getSourceAsMap();
      System.out.println(sourceAsMap);
    }
  }
}
