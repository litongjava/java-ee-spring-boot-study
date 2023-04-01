package top.ppnt.study.spring.boot.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.litongjava.utils.string.StringUtils;
import com.litongjava.utils.vo.PageJsonBean;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.study.spring.boot.elasticsearch.model.User;
@Service
@Slf4j
public class StudentService {

  // 索引名称
  String indexName = "user";
  @Autowired
  private RestHighLevelClient restHighLevelClient;

  public PageJsonBean<User> mohues(String keyword, int pageNo, int pageSize) throws IOException {

    SearchRequest searchRequest = new SearchRequest(indexName);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    // 判断是否存在
    if (StringUtils.isEmpty(keyword)) {
      // 不存在查全部
      searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    } else {
      BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
      boolQuery.should(QueryBuilders.wildcardQuery("name", keyword+"*"));
      //如果有多个添加可以通过上面的方式添加
      //age是数字类型转换事出现错误
      //boolQuery.should(QueryBuilders.wildcardQuery("age", keyword+"*"));
      searchSourceBuilder.query(boolQuery);
      
    }
    // 分页
    searchSourceBuilder.from((pageNo - 1) * pageSize);
    searchSourceBuilder.size(pageSize);
    // 高亮
    if (StringUtils.isNotEmpty(keyword)) {
      HighlightBuilder highlightBuilder = new HighlightBuilder();
      highlightBuilder.field("name").field("age");
      highlightBuilder.preTags("<span style='color:red'>");
      highlightBuilder.postTags("</span>");
      searchSourceBuilder.highlighter(highlightBuilder);
    }
    // soruce
    searchRequest.source(searchSourceBuilder);
    // 执行查询
    SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    // 处理结果
    SearchHits hits = search.getHits();
    TotalHits totalHits = hits.getTotalHits();
    // 所有数量
    long count = totalHits.value;
    List<User> results = new ArrayList<>();
    // 遍历查询结果
    SearchHit[] searchHits = hits.getHits();
    for (SearchHit hit : searchHits) {
      String index = hit.getIndex();
      String id = hit.getId();
      float score = hit.getScore();
      // 拿到json字符串
      String string = hit.getSourceAsString();
      log.info("hit:{},{},{},{}",index,id,score,string);
      // json转对象
      User entity = JSON.parseObject(string, User.class);
      entity.setId(id);
      if (StringUtils.isNotEmpty(keyword)) {
        // 处理高亮
        // 获取高亮字段
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        log.info("size:{}", highlightFields.size());
        //遍历输出高亮信息
        for(Map.Entry<String, HighlightField> e : highlightFields.entrySet()) {
          String key = e.getKey();
          HighlightField highlightField = e.getValue();
          Text[] fragments = highlightField.getFragments();
          log.info("{},{}",key,fragments);
        }
      }
      results.add(entity);
    }
    // 分页
    PageJsonBean<User> pageJsonBean = new PageJsonBean<>(count, pageNo, pageSize, results);
    return pageJsonBean;
  }
}
