package com.litongjava.spring.boot.milton.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.litongjava.spring.boot.milton.config.milton.IpWhitelist;

import io.milton.config.HttpManagerBuilder;
import io.milton.http.HttpManager;
import io.milton.http.Request;
import io.milton.http.ResourceFactory;
import io.milton.http.Response;
import io.milton.http.annotated.AnnotationResourceFactory;
import io.milton.http.template.JspViewResolver;
import io.milton.http.template.ViewResolver;
import io.milton.servlet.MiltonServlet;
import lombok.extern.slf4j.Slf4j;

/**
 * 定义一个集成Servlet Filter的超类
 * 其实使用 {@link io.milton.servlet.SpringMiltonFilter}是一个更好的选择
 */
@Slf4j
public class SpringMiltonFilterBean extends GenericFilterBean {
  @Autowired
  private MiltonProperties miltonProperties;

  @Autowired
  private HttpManagerBuilder httpManagerBuilder;

  private HttpManager httpManager;

  @Override
  protected void initFilterBean() throws ServletException {
    super.initFilterBean();
    //ResourceFactory
    log.info("httpManagerBuilder:{}", httpManagerBuilder);
    ResourceFactory rf = httpManagerBuilder.getMainResourceFactory();
    log.info("resourceFactory:{}", rf);
    if (rf instanceof AnnotationResourceFactory) {
      //AnnotationResourceFactory
      AnnotationResourceFactory arf = (AnnotationResourceFactory) rf;
      if (arf.getViewResolver() == null) {
        //ViewResolver
        ViewResolver viewResolver = new JspViewResolver(this.getServletContext());
        log.info("viewResolver:{}", viewResolver);
        arf.setViewResolver(viewResolver);
      }
    }
    this.httpManager = httpManagerBuilder.buildHttpManager();
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // 1.不是http请求
    if (!(servletRequest instanceof HttpServletRequest)) {
      chain.doFilter(servletRequest, response);
      return;
    }

    // 2.是http请求，访问的特殊文件路径，直接当做普通请求
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String url = request.getRequestURI();
    if (miltonProperties.getExcludePaths() != null) {
      for (String s : miltonProperties.getExcludePaths()) {
        if (url.startsWith(s)) {
          chain.doFilter(servletRequest, response);
          return;
        }
      }
    }

    // 3.是http请求，访问的普通路径，走webdav协议
    checkIP(request);
    doMiltonProcessing(request, (HttpServletResponse) response);
  }

  private void checkIP(HttpServletRequest request) {
    String remoteAddr = request.getRemoteAddr();
    if (!IpWhitelist.cache.contains(remoteAddr)) {
      throw new RuntimeException("Have not logged in on this machine, prohibit access");
    }
  }

  private void doMiltonProcessing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      MiltonServlet.setThreadlocals(req, resp);
      Request request = new io.milton.servlet.ServletRequest(req, this.getServletContext());
      Response response = new io.milton.servlet.ServletResponse(resp);
      httpManager.process(request, response);
    } finally {
      MiltonServlet.clearThreadlocals();
      resp.flushBuffer();
    }
  }

  @Override
  public void destroy() {
    if (httpManager != null) {
      httpManager.shutdown();
    }
  }
}
