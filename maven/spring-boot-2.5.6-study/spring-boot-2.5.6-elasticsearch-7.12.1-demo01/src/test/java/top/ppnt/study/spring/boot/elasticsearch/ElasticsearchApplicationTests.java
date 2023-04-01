package top.ppnt.study.spring.boot.elasticsearch;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ElasticsearchApplicationTests {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  // 创建索引 PUT zoomy_index
  @Test
  void createIndex() throws IOException {
    // client
    IndicesClient indicesClient = restHighLevelClient.indices();
    // request
    CreateIndexRequest request = new CreateIndexRequest("zoomy_index");
    // create
    CreateIndexResponse response = indicesClient.create(request, RequestOptions.DEFAULT);
    //
    System.out.println(response);
  }

  // 判断索引是否存在
  @Test
  void getIndex() throws IOException {
    // client
    IndicesClient indicesClient = restHighLevelClient.indices();
    // request
    GetIndexRequest request = new GetIndexRequest("zoomy_index");
    // exists
    boolean exists = indicesClient.exists(request, RequestOptions.DEFAULT);
    System.out.println(exists);
  }

  // 删除索引
  @Test
  void deleteIndex() throws IOException {
    // client
    IndicesClient indicesClient = restHighLevelClient.indices();
    // request
    DeleteIndexRequest request = new DeleteIndexRequest("zoomy_index");
    // delete
    AcknowledgedResponse delete = indicesClient.delete(request, RequestOptions.DEFAULT);
    // isAcknowledged
    System.out.println(delete.isAcknowledged());
  }
}