package com.ppnt.spring.security;

import java.security.Signature;
import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.*;

/**
 * @author create by Ping E Lee on 2022年3月31日 上午11:32:03
 */
public class JwtTokenDemo01 {

  private String signingKey = "robot_123456#";

  public static void main(String[] args) {
    JwtTokenDemo01 jwtTokenDemo01 = new JwtTokenDemo01();
//		System.out.println(jwtTokenDemo01.createToken());
    System.out.println(jwtTokenDemo01.parseToken());
  }

  /**
   * 创建 Token
   *
   * @return
   */
  public String createToken() {
    //builder
    JwtBuilder builder = Jwts.builder();
    //设置builder参数
    builder.setId("test01").setSubject("Ping").setAudience("李通").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, signingKey);

    HashMap<String, Object> map = new HashMap<>();
    map.put("ha", "哈哈哈");
    builder.addClaims(map);
    //构建
    return builder.compact();
  }

  /**
   * 解析token
   *
   * @return
   */
  public String parseToken() {
    String compactJwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZXN0MDEiLCJzdWIiOiJQaW5nIiwiYXVkIjoi5p2O6YCaIiwiaWF0IjoxNjQ4Njk3ODUyLCJoYSI6IuWTiOWTiOWTiCJ9.FdAnk-RvUHjURKo3SmaBNKAJkpkYfwpEbt0i3yfQclU";
    //解析器
    JwtParser parser = Jwts.parser();
    //设置解析器参数
    parser.setSigningKey(signingKey);
    //解析
    Jws<Claims> claimsJws = parser.parseClaimsJws(compactJwt);
    //获取解析的body部分
    Claims body = claimsJws.getBody();
    return body.toString();
  }
}
