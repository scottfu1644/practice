package com.randstad.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * Function: Spring 工具类，提供Spring配置文件的读取操�?. <br>
 * 
 * @author suzu 2014-1-24 上午9:12:48
 */
public class SpringUtil {
  public static ApplicationContext loadApplicationContext() {
    return loadApplicationContext("applicationContext*.xml");
  }

  public static ApplicationContext loadApplicationContext(String filePattern) {
    ApplicationContext ctx =
        new ClassPathXmlApplicationContext(new String[] {"classpath*:" + filePattern});
    return ctx;
  }
}
