package com.liotngjava.spring.boot.redis.cluter.config;

import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyRedissonConfig {
  @Autowired(required = false)
  private RedisConfigProperties redisConfigProperties;

  /**
   * 配置redisson集群
   * @return
   */
  @Bean(destroyMethod = "shutdown")
  public RedissonClient getRedissonClient() {
    List<String> clusterNodes = redisConfigProperties.getCluster().getNodeAddresses();
    log.info("【Redisson 配置】：{}", redisConfigProperties);

    Config config = new Config();
    // 对象编码选择纯字符串编码
    config.setCodec(StringCodec.INSTANCE);
    
    //添加redis节点
    String[] array = clusterNodes.toArray(new String[clusterNodes.size()]);
    ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress(array);
        
    // 设置密码
    clusterServersConfig.setPassword(redisConfigProperties.getPassword());
    // redis连接心跳检测，防止一段时间过后，与redis的连接断开
    clusterServersConfig.setPingConnectionInterval(32000);
    //创建客户端
    return Redisson.create(config);
  }

  /**
   * 单机配置
   * @return
   * @throws IOException
   */
//  @Bean(destroyMethod = "shutdown")
//  RedissonClient singleRedisson() throws IOException {
//    log.info("【Redisson 配置】：{}", redisConfigProperties); // 创建配置
//    Config config = new Config();
//    config.setCodec(StringCodec.INSTANCE);
//    config.setTransportMode(TransportMode.NIO);
//    SingleServerConfig singleServerConfig = config.useSingleServer()
//        .setAddress(redisConfigProperties.getSingle().getAddress())
//        .setDatabase(redisConfigProperties.getSingle().getDatabase());
//
//    return Redisson.create(config);
//  }

}