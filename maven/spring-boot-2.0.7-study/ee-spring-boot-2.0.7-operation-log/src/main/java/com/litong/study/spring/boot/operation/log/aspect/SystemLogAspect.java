package com.litong.study.spring.boot.operation.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.litong.study.spring.boot.operation.log.annotation.SystemControllerLog;
import com.litong.study.spring.boot.operation.log.annotation.SystemServiceLog;
import com.litong.study.spring.boot.operation.log.bean.Action;
import com.litong.study.spring.boot.operation.log.bean.User;
import com.litong.study.spring.boot.operation.log.servie.ActionService;
import com.litong.study.spring.boot.operation.log.utils.IpUtils;
import com.litong.study.spring.boot.operation.log.utils.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 编写切面
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
  // 注入Service用于把日志保存数据库，实际项目入库采用队列做异步
  @Resource
  private ActionService actionService;

  // Service层切点
  @Pointcut("@annotation(com.litong.study.spring.boot.operation.log.annotation.SystemServiceLog)")
  public void serviceAspect() {
  }

  // Controller层切点
  @Pointcut("@annotation(com.litong.study.spring.boot.operation.log.annotation.SystemControllerLog)")
  public void controllerAspect() {
  }

  /**
   * @Description 前置通知 用于拦截Controller层记录用户的操作
   */

  @Before("controllerAspect()")
  public void doBefore(JoinPoint joinPoint) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    // 读取session中的用户
    User user = (User) session.getAttribute("user");
    if (user == null)
      user = new User().setId("001").setUsername("none");

    String ip = IpUtils.getIpAddr(request);

    try {
      // *========控制台输出=========*//
      log.info("==============前置通知开始==============");
      Object target = joinPoint.getTarget();
      Signature signature = joinPoint.getSignature();
      // com.litong.study.spring.boot.operation.log.controller.UserContorller.getUser
      log.info("请求方法" + (target.getClass().getName() + "." + signature.getName()));
      // 获取用户
      String desc = getControllerMethodDescription(joinPoint);
      log.info("方法描述：" + desc);
      log.info("请求人：" + user.getUsername());
      log.info("请求ip：" + ip);

      // *========数据库日志=========*//
      Action action = new Action();
      action.setActionDesc(desc);
      action.setActionType("0");
      action.setActionIp(ip);
      action.setUserId(user.getId());
      action.setActionTime(new Date());
      // 保存数据库
      actionService.save(action);

    } catch (Exception e) {
      // 记录本地异常日志
      log.error("==前置通知异常==");
      log.error("异常信息：{}", e.getMessage());
    }
  }

  /**
   * @Description 异常通知 用于拦截service层记录异常日志
   */
  @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
  public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    // 读取session中的用户
    User user = (User) session.getAttribute("user");
    // 获取请求ip
    String ip = IpUtils.getIpAddr(request);
    // 获取用户请求方法的参数并序列化为JSON格式字符串
    String params = "";
    if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
      for (int i = 0; i < joinPoint.getArgs().length; i++) {
        params += JsonUtils.objectToJson(joinPoint.getArgs()[i]) + ";";
      }
    }
    try {
      /* ========控制台输出========= */
      log.info("=====异常通知开始=====");
      log.info("异常代码:" + e.getClass().getName());
      log.info("异常信息:" + e.getMessage());
      Object target = joinPoint.getTarget();
      Signature signature = joinPoint.getSignature();
      log.info("异常方法:" + (target.getClass().getName() + "." + signature.getName() + "()"));
      log.info("方法描述:" + getServiceMethodDescription(joinPoint));
      log.info("请求人:" + user.getUsername());
      log.info("请求IP:" + ip);
      log.info("请求参数:" + params);
      /* ==========数据库日志========= */
      Action action = new Action();
      action.setActionDesc(getServiceMethodDescription(joinPoint));
      action.setActionType("1");
      action.setUserId(user.getId());
      action.setActionIp(ip);
      action.setActionTime(new Date());
      // 保存到数据库
      actionService.save(action);
    } catch (Exception ex) {
      // 记录本地异常日志
      log.error("==异常通知异常==");
      log.error("异常信息:{}", ex.getMessage());
    }
  }

  /**
   * @Description 获取注解中对方法的描述信息 用于service层注解
   */
  public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Class<?> targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    String description = "";
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Class<?>[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {
          description = method.getAnnotation(SystemServiceLog.class).description();
          break;
        }
      }
    }
    return description;
  }

  /**
   * @Description 获取注解中对方法的描述信息 用于Controller层注解
   */
  public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
    String className = joinPoint.getTarget().getClass().getName();
    Class<?> targetClass = Class.forName(className);
    Method[] methods = targetClass.getMethods();

    String methodName = joinPoint.getSignature().getName();// 目标方法名
    Object[] arguments = joinPoint.getArgs(); // 获取目标参数

    String description = "";
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Class<?>[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {
          description = method.getAnnotation(SystemControllerLog.class).description();
          break;
        }
      }
    }
    return description;
  }
}