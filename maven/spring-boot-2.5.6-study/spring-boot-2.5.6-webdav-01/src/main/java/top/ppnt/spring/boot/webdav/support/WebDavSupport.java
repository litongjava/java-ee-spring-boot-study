package top.ppnt.spring.boot.webdav.support;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Globals;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.servlets.WebdavServlet;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author hex.wang
 * @Class WebDavSupport
 * @Description
 * @Date 2022/1/5 14:45
 */
@Slf4j
@SuppressWarnings("serial")
@WebServlet(name = "WebDavSupport", urlPatterns = { UploadConstant.WEB_DAV_URL },
    //
    initParams = {
        //
        @WebInitParam(name = "listings", value = "true"),
        //
        @WebInitParam(name = "readonly", value = "false"),
        //
        @WebInitParam(name = "debug", value = "0")
    //
    })

public class WebDavSupport extends WebdavServlet {
  @Value("${config.baseDav}")
  private String baseDav;
  @Value("${config.user}")
  private String user;
  @Value("${config.password}")
  private String password;

  @Override
  public void init() throws ServletException {
    WebResourceRoot webResourceRoot = (WebResourceRoot) getServletContext().getAttribute(Globals.RESOURCES_ATTR);
    File additionWebInfClasses = new File(baseDav);
    String absolutePath = additionWebInfClasses.getAbsolutePath();
    webResourceRoot.addPreResources(new DirResourceSet(webResourceRoot, "/", absolutePath, "/"));
    super.init();
  }

  public boolean auth(ServletRequest req, ServletResponse res) {
    String authorization = ((HttpServletRequest) req).getHeader("Authorization");
    if (authorization != null) {
      log.info("user:{},password:{}",user,password);
      String base64 = authorization.replaceFirst("Basic\\s+", "");
      String string = new String(Base64.decodeBase64(base64), Charset.forName("UTF-8"));
      String array[] = string.split(":");
      if (array.length == 2 && user.equals(array[0]) && password.equals(array[1])) {
        return true;
      }
    }
    HttpServletResponse res1 = (HttpServletResponse) res;
    res1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// 401
    res1.setCharacterEncoding("UTF-8");
    res1.setHeader("WWW-Authenticate", "Basic realm=\"DAV\"");
    return false;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    if (auth(req, res)) {
      super.service(req, res);
    }
  }
}