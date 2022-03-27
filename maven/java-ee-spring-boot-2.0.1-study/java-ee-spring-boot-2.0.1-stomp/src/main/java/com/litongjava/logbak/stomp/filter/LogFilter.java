package com.litongjava.logbak.stomp.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.litongjava.logbak.stomp.model.LoggerMessage;
import com.litongjava.logbak.stomp.queue.LoggerQueue;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogFilter extends Filter<ILoggingEvent> {
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public FilterReply decide(ILoggingEvent event) {
    String exception = null;
    IThrowableProxy proxy = event.getThrowableProxy();
    if (proxy != null) {
      exception = "<span class='excehtext'>" + proxy.getClassName() + " " + proxy.getMessage() + "</span></br>";

      StackTraceElementProxy[] array = proxy.getStackTraceElementProxyArray();
      for (int i = 0; i < array.length; i++) {
        exception += "<span class='excetext'>" + array[i].toString() + "</span></br>";
      }
    }
    String message = getLogMessage(event, exception);

    LoggerQueue.getInstance().push(message);
    return FilterReply.ACCEPT;
  }

  /**
   * 
   * @param event
   * @param exception
   * @return 格式化后的字符串 eg 2021-10-10 20:28:09.744 [http-nio-8080-exec-7] INFO
   *         IndexController.indexEmpyt:15 - this is index
   */
  public String getLogMessage(ILoggingEvent event, String exception) {
    String timeString = sdf.format(new Date(event.getTimeStamp()));
    LoggerMessage message = new LoggerMessage();
    message.setTimeString(timeString);
    message.setThreadName(event.getThreadName());
    message.setLogLevel(event.getLevel().levelStr);
    message.setClassName(event.getLoggerName());
    message.setMessage(event.getFormattedMessage());
    message.setException(exception);
    return message.format();
  }
}