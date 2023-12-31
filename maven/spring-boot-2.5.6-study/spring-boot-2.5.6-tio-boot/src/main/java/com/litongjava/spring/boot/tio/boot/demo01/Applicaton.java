package com.litongjava.spring.boot.tio.boot.demo01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@SpringBootApplication
@AComponentScan
public class Applicaton {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(Applicaton.class, args);

    List<String> list = new ArrayList<String>();
    for (int i = 0; i < args.length; i++) {
      list.add(args[i]);
    }
    list.add("--tio.noServer=true");
    String[] newArgs = list.toArray(new String[] {});

    TioApplication.run(Applicaton.class, newArgs);
    long end = System.currentTimeMillis();
    System.out.println(end - start + "(ms)");
  }
}