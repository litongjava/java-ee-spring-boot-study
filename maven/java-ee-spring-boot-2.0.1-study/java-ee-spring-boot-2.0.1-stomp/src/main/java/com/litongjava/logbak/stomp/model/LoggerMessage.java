package com.litongjava.logbak.stomp.model;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * 日志格式 2021-10-10 20:28:09.744 [http-nio-8080-exec-7] INFO
 * IndexController.indexEmpyt:15 - this is index
 *
 */
public class LoggerMessage {
  // time [threadName] Level
  // com.litongjava.logbak.stomp.controller.IndexController :this is index
  private String pattern = "%s [%s] %s %s.%s:%s - %s %s";
  private String timeString;
  private String threadName;
  private String logLevel;
  private String className;
  private String methodName;
  private String lineNumber;
  private String message;
  private String exception;

  /**
   * 返回格式化后的字符串
   * 
   * @return
   */
  public String format() {
    if (StringUtils.isEmpty(exception)) {
      exception = "";
    }
    return String.format(pattern, timeString, threadName, logLevel, className, methodName, lineNumber, message,
        exception);
  }
}