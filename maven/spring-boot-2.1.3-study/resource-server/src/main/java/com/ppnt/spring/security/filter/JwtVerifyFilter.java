package com.ppnt.spring.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.ppnt.spring.boot.config.RsaPublicKeyProperties;
import top.ppnt.spring.boot.model.SysUser;
import top.ppnt.spring.boot.utils.jwt.JwtUtils;
import top.ppnt.spring.boot.utils.jwt.Payload;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

  private RsaPublicKeyProperties rsaKeyProperties;

  public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaPublicKeyProperties rsaKeyProperties) {
    super(authenticationManager);
    this.rsaKeyProperties = rsaKeyProperties;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");

    // 没有登录
    if (header == null || !header.startsWith("Token ")) {
      chain.doFilter(request, response);
      response.setContentType("application/json;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      PrintWriter out = response.getWriter();
      Map<String, Object> map = new HashMap<String, Object>(4);
      map.put("code", HttpServletResponse.SC_FORBIDDEN);
      map.put("message", "请登录！");
      out.write(new ObjectMapper().writeValueAsString(map));
      out.flush();
      out.close();
      return;
    }
    // 登录之后从token中获取用户信息
    String token = header.replace("Token ", "");
    Payload<SysUser> payload = JwtUtils.getInfoFromToken(token, rsaKeyProperties.getPublicKey(), SysUser.class);
    SysUser sysUser = payload.getUserInfo();
    
    //将用户信息添加到Authentication
    if (sysUser != null) {
      Authentication authResult = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null,
          sysUser.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authResult);
      chain.doFilter(request, response);
    }
  }
}
