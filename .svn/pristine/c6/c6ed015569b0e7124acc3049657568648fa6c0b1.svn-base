package com.randstad.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 
 * Function: 异常配置类. <br>
 * 
 * @author suzu
 */
@Configuration
// @ComponentScan("com")
public class ApplicationConfig {
  @Bean
  public ResourceBundleMessageSource exceptionMessageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("i18n/exceptions");
    source.setUseCodeAsDefaultMessage(true);
    return source;
  }
}
