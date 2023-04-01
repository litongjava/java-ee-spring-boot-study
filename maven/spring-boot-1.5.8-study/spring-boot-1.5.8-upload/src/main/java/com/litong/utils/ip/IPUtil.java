package com.litong.utils.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IPUtil {
  /**
   * 获取本机局域网IP地址
   */
  public static InetAddress getLocalHostLANAddress() {
    // 1.获取所有网络接口
    Enumeration<NetworkInterface> networkInterfaces = null;
    try {
      networkInterfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e1) {
      e1.printStackTrace();
    }
    InetAddress candidateAddress = null;
    // 2.遍历网络接口
    while (networkInterfaces.hasMoreElements()) {
      NetworkInterface networkInterface = networkInterfaces.nextElement();
      Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
      // 3.遍历所有网络接口下的所有IP
      while (inetAddresses.hasMoreElements()) {
        InetAddress inetAddress = inetAddresses.nextElement();
        // 4.排除loopback类型地址,取出是site-local地址
        if (isLocalIf(inetAddress)) {
          return inetAddress;
        } else {// 5.site-local类型的地址未被发现，先记录候选地址
          candidateAddress = inetAddress;
        }
      }
    }
    if (candidateAddress != null) {
      return candidateAddress;
    }

    // 6.如果没有发现 non-loopback地址.只能用最次选的方案
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      return localHost;
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    // 7.最后返回null
    return null;
  }

  /**
   * 如果是本地网卡,返回true
   */
  private static boolean isLocalIf(InetAddress inetAddress) {
    return inetAddress.isSiteLocalAddress() && !inetAddress.getHostAddress().equals("172.17.0.1");
  }

  /**
   * 输出访问地址
   * 
   * @param port
   * @param contextPath
   */
  public static String getThisUrl(int port, String contextPath) {
    String ip = getLocalHostLANAddress().getHostAddress();
    String retval = "http://" + ip + ":" + port + contextPath;
    return retval;
  }
}