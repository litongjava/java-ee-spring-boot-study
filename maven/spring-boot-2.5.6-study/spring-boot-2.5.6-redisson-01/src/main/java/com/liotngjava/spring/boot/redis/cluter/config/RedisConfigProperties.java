package com.liotngjava.spring.boot.redis.cluter.config;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties implements Serializable {

  private static final long serialVersionUID = 8815222005846355408L;
  private String password;
  private cluster cluster;
  private Single single;

  public static class cluster {
    private List<String> nodeAddresses;

    public List<String> getNodeAddresses() {
      return nodeAddresses;
    }

    public void setNodeAddresses(List<String> nodeAddresses) {
      this.nodeAddresses = nodeAddresses;
    }

    @Override
    public String toString() {
      return "{" + "nodeAddresses=" + nodeAddresses + '}';
    }
  }

  public static class Single {
    private String address;
    public Integer database;

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public Integer getDatabase() {
      return database;
    }

    public void setDatabase(Integer database) {
      this.database = database;
    }

    @Override
    public String toString() {
      return "Single{" + "address='" + address + '\'' + ", database=" + database + '}';
    }
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RedisConfigProperties.cluster getCluster() {
    return cluster;
  }

  public void setCluster(RedisConfigProperties.cluster cluster) {
    this.cluster = cluster;
  }
}