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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.spring.boot.config.RsaKeyProperties;
import top.ppnt.spring.boot.model.SysUser;
import top.ppnt.spring.boot.utils.jwt.JwtUtils;

/**
 * 认证过滤器
 */
@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private RsaKeyProperties rsaKeyProperties;

  public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
    this.authenticationManager = authenticationManager;
    this.rsaKeyProperties = rsaKeyProperties;
  }

  /**
   * 这个方法是用来去尝试验证用户的，父类中是从POST请求的form表单中获取，但是这里不是，所以需要重写
   * @param request
   * @param response
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      //解析请求,获取用名和密码
      SysUser user = JSONObject.parseObject(request.getInputStream(), SysUser.class);
      
      //生成验证信息
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
          user.getPassword());
      //进行验证
      return authenticationManager.authenticate(authentication);
    } catch (Exception e) {
      try {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("message", "账号或密码错误！");
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      throw new RuntimeException(e);
    }
  }

  /**
   * 成功之后执行的方法，父类中是放入session，不符合我们的要求，所以重写
   * @param request
   * @param response
   * @param chain
   * @param authResult
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    log.info("authResult:{}", authResult);
    
    //获取用户
    Object sysUser = authResult.getPrincipal();
    
    //使用私钥对用户信息进行加密,返回Token
    String token = JwtUtils.generateTokenExpireInMinutes(sysUser,rsaKeyProperties.getPrivateKey(), 24 * 60);
    response.addHeader("Authorization", "Token " + token);
    try {
      // 登录成功时，返回json格式进行提示
      response.setContentType("application/json;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
      PrintWriter out = response.getWriter();
      Map<String, Object> map = new HashMap<String, Object>(4);
      map.put("code", HttpServletResponse.SC_OK);
      map.put("message", "登陆成功！");
      out.write(new ObjectMapper().writeValueAsString(map));
      out.flush();
      out.close();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}
