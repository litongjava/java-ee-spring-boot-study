package top.ppnt.study.spring.boot.elasticsearch.config;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.litongjava.utils.string.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ElasticsearchClientConfig {
  /**
   * 超时时间设为5分钟
   */
  private static final int TIME_OUT = 5 * 60 * 1000;
  private static final int ADDRESS_LENGTH = 2;
  private static final String HTTP_SCHEME = "http";

  @Value("${elasticsearch.ip}")
  String[] ipAddress;

  @Bean
  public RestClientBuilder restClientBuilder() {
    log.info("ipAddress:{}", Arrays.toString(ipAddress));
    // 使用stream流进行操作
    Stream<String> stream = Arrays.stream(ipAddress);
    HttpHost[] hosts = stream.map(this::makeHttpHost).filter(Objects::nonNull).toArray(HttpHost[]::new);
    return RestClient.builder(hosts);
  }

  @Bean(name = "highLevelClient")
  public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
    // requestConfigCallback
    RestClientBuilder.RequestConfigCallback requestConfigCallback = new RestClientBuilder.RequestConfigCallback() {
      @Override
      public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
        return requestConfigBuilder.setSocketTimeout(TIME_OUT);
      }
    };
    // restClientBuilder
    restClientBuilder.setRequestConfigCallback(requestConfigCallback);
    // RestHighLevelClient 此处可以进行其它操作
    return new RestHighLevelClient(restClientBuilder);
  }

  private HttpHost makeHttpHost(String s) {
    assert StringUtils.isNotEmpty(s);
    String[] address = s.split(":");
    if (address.length == ADDRESS_LENGTH) {
      String ip = address[0];
      int port = Integer.parseInt(address[1]);
      log.info("es server:{}:{}", ip, port);
      return new HttpHost(ip, port, HTTP_SCHEME);
    } else {
      return null;
    }
  }
}