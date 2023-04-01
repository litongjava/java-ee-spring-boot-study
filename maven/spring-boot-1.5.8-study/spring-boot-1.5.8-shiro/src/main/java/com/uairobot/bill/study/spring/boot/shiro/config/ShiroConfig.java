package com.uairobot.bill.study.spring.boot.shiro.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uairobot.bill.study.spring.boot.shiro.filter.ShiroLogoutFilter;
import com.uairobot.bill.study.spring.boot.shiro.service.impl.CustomRealm;

@Configuration
public class ShiroConfig {
  @Bean
  @ConditionalOnMissingBean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
    defaultAAP.setProxyTargetClass(true);
    return defaultAAP;
  }

  // 将自己的验证方式加入容器
  @Bean
  public CustomRealm myShiroRealm() {
    CustomRealm customRealm = new CustomRealm();
    return customRealm;
  }

  // 权限管理，配置主要是Realm的管理认证
  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(myShiroRealm());
    return securityManager;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }

  /**
   * ShiroFilterFactoryBean 处理拦截资源文件问题。
   * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
   * @param securityManager
   * @return
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    Map<String, String> map = new HashMap<>();
    // 登出
    map.put("/logout", "logout");
    // 对所有用户认证
    map.put("/**", "authc");
    // 登录
    shiroFilterFactoryBean.setLoginUrl("/login");
    // 首页
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 错误页面，认证不通过跳转
    shiroFilterFactoryBean.setUnauthorizedUrl("/error");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
    filters.put("logout", shiroLogoutFilter());
    return shiroFilterFactoryBean;
  }
  
  /**
   * 配置LogoutFilter
   * @return
   */
  public ShiroLogoutFilter shiroLogoutFilter() {
    ShiroLogoutFilter shiroLogoutFilter = new ShiroLogoutFilter();
    // 配置登出后重定向的地址，等出后配置跳转到登录接口
    shiroLogoutFilter.setRedirectUrl("/login");
    return shiroLogoutFilter;
  }
}