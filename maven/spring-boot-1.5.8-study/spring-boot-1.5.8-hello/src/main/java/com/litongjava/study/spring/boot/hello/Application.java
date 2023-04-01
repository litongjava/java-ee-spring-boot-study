package com.litongjava.study.spring.boot.hello;
import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Application.class, args);
    //SpringApplicationWrapper.run(Application.class, args,true);

    long end = System.currentTimeMillis();
    System.out.println((end-start)+"ms");
  }
}
/*
D:\dev_program\java\jdk1.8.0_121\bin\java.exe -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -javaagent:D:\dev_program\ideaIU-2019.3.3.win\lib\idea_rt.jar=12157:D:\dev_program\ideaIU-2019.3.3.win\bin -Dfile.encoding=UTF-8 -classpath D:\dev_program\java\jdk1.8.0_121\jre\lib\charsets.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\deploy.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\access-bridge-64.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\cldrdata.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\dnsns.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\jaccess.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\jfxrt.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\localedata.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\nashorn.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\sunec.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\sunjce_provider.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\sunmscapi.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\sunpkcs11.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\ext\zipfs.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\javaws.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\jce.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\jfr.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\jfxswt.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\jsse.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\management-agent.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\plugin.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\resources.jar;D:\dev_program\java\jdk1.8.0_121\jre\lib\rt.jar;E:\dev_workspace\java\java-study\java-ee-spring-boot-study\java-ee-spring-boot-1.5.8-study\ee-spring-boot-1.5.8-hello\target\classes;D:\dev_mavenRepository\com\litongjava\hotswap-classloader\1.0.3\hotswap-classloader-1.0.3.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot-starter-web\1.5.8.RELEASE\spring-boot-starter-web-1.5.8.RELEASE.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot-starter\1.5.8.RELEASE\spring-boot-starter-1.5.8.RELEASE.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot\1.5.8.RELEASE\spring-boot-1.5.8.RELEASE.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot-autoconfigure\1.5.8.RELEASE\spring-boot-autoconfigure-1.5.8.RELEASE.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot-starter-logging\1.5.8.RELEASE\spring-boot-starter-logging-1.5.8.RELEASE.jar;D:\dev_mavenRepository\ch\qos\logback\logback-classic\1.1.11\logback-classic-1.1.11.jar;D:\dev_mavenRepository\ch\qos\logback\logback-core\1.1.11\logback-core-1.1.11.jar;D:\dev_mavenRepository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;D:\dev_mavenRepository\org\slf4j\jcl-over-slf4j\1.7.25\jcl-over-slf4j-1.7.25.jar;D:\dev_mavenRepository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;D:\dev_mavenRepository\org\slf4j\log4j-over-slf4j\1.7.25\log4j-over-slf4j-1.7.25.jar;D:\dev_mavenRepository\org\springframework\spring-core\4.3.12.RELEASE\spring-core-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;D:\dev_mavenRepository\org\springframework\boot\spring-boot-starter-tomcat\1.5.8.RELEASE\spring-boot-starter-tomcat-1.5.8.RELEASE.jar;D:\dev_mavenRepository\org\apache\tomcat\embed\tomcat-embed-core\8.5.23\tomcat-embed-core-8.5.23.jar;D:\dev_mavenRepository\org\apache\tomcat\tomcat-annotations-api\8.5.23\tomcat-annotations-api-8.5.23.jar;D:\dev_mavenRepository\org\apache\tomcat\embed\tomcat-embed-el\8.5.23\tomcat-embed-el-8.5.23.jar;D:\dev_mavenRepository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.23\tomcat-embed-websocket-8.5.23.jar;D:\dev_mavenRepository\org\hibernate\hibernate-validator\5.3.5.Final\hibernate-validator-5.3.5.Final.jar;D:\dev_mavenRepository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;D:\dev_mavenRepository\org\jboss\logging\jboss-logging\3.3.1.Final\jboss-logging-3.3.1.Final.jar;D:\dev_mavenRepository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;D:\dev_mavenRepository\com\fasterxml\jackson\core\jackson-databind\2.8.10\jackson-databind-2.8.10.jar;D:\dev_mavenRepository\com\fasterxml\jackson\core\jackson-annotations\2.8.0\jackson-annotations-2.8.0.jar;D:\dev_mavenRepository\com\fasterxml\jackson\core\jackson-core\2.8.10\jackson-core-2.8.10.jar;D:\dev_mavenRepository\org\springframework\spring-web\4.3.12.RELEASE\spring-web-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\springframework\spring-aop\4.3.12.RELEASE\spring-aop-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\springframework\spring-beans\4.3.12.RELEASE\spring-beans-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\springframework\spring-context\4.3.12.RELEASE\spring-context-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\springframework\spring-webmvc\4.3.12.RELEASE\spring-webmvc-4.3.12.RELEASE.jar;D:\dev_mavenRepository\org\springframework\spring-expression\4.3.12.RELEASE\spring-expression-4.3.12.RELEASE.jar com.litongjava.study.spring.boot.hello.Application
java.lang.NoSuchMethodException: org.springframework.boot.SpringApplication.run(java.lang.Class, [Ljava.lang.String;)
	at java.lang.Class.getMethod(Class.java:1786)
	at com.litongjava.hotswap.kit.ReflectionUtils.getMethod(ReflectionUtils.java:97)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.runDev(SpringApplicationWrapper.java:85)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.run(SpringApplicationWrapper.java:51)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.run(SpringApplicationWrapper.java:33)
	at com.litongjava.study.spring.boot.hello.Application.main(Application.java:11)
Exception in thread "main" java.lang.NullPointerException
	at com.litongjava.hotswap.kit.ReflectionUtils.invoke(ReflectionUtils.java:137)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.runDev(SpringApplicationWrapper.java:86)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.run(SpringApplicationWrapper.java:51)
	at com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper.run(SpringApplicationWrapper.java:33)
	at com.litongjava.study.spring.boot.hello.Application.main(Application.java:11)

Process finished with exit code 1

 */
