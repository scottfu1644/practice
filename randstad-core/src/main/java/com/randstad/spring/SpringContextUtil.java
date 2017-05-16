package com.randstad.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 
 * Project Name: walker-spring <br>
 * Package Name: com.randstad.spring <br>
 * ClassName: SpringContextUtil <br>
 * Function: 获取spring容器，以访问容器中定义的其他bean. <br>
 * Date: 2014-1-24 上午8:58:26 <br>
 * 
 * 
 * @author Walker mailto: zuwei.su@qq.com 2014-1-24 上午8:58:26
 */
public class SpringContextUtil implements ApplicationContextAware {
  private static ApplicationContext applicationContext; // Spring应用上下文环�?

  /**
   * 实现ApplicationContextAware接口的回调方法，设置上下文环�?
   * 
   * @param applicationContext
   * @throws BeansException
   */
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    // 通过静态方法设置静态变量值，不能直接设置静态变量值，解决findbugs的 ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD检查.
    SpringContextUtil.setSpringContext(applicationContext);
  }

  /**
   * 获得静态Sring上下文变量
   * 
   * @return ApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 
   * 设置静态变量值，解决findbugs的 ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD检查.
   * 
   * @param context Spring应用程序上下文
   */
  public static void setSpringContext(ApplicationContext context) {
    SpringContextUtil.applicationContext = context;
  }

  /**
   * 获取对象
   * 
   * @param name
   * @return Object �?个以�?给名字注册的bean的实�?
   * @throws BeansException
   */
  public static Object getBean(String name) throws BeansException {
    return applicationContext.getBean(name);
  }

  /**
   * 获取类型为requiredType的对�? 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException�?
   * 
   * @param name bean名称
   * @param requiredType bean对象类型
   * @return Object 返回requiredType类型对象
   * @throws BeansException
   */
  public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
    return applicationContext.getBean(name, requiredType);
  }

  /**
   * 获取类型为requiredType的对�? 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException�?
   * 
   * @param requiredType 返回对象类型
   * @return Object 返回requiredType类型对象
   * @throws BeansException
   */
  public static <T> T getBean(Class<T> requiredType) throws BeansException {
    return applicationContext.getBean(requiredType);
  }

  /**
   * 
   * 返回与类相关的bean名称及示例映�?. <br>
   * {@link ApplicationContext.getBeansOfType}
   * 
   * @param requiredType bean类型
   * @return 类相关的bean名称及示例映�?
   * @throws BeansException
   */
  public static <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {
    return applicationContext.getBeansOfType(requiredType);
  }

  /**
   * 是否存在名称为name的bean
   * 
   * @param name bean名称
   * @return 是否存在，如果BeanFactory包含�?个与�?给名称匹配的bean定义，则返回true
   */
  public static boolean containsBean(String name) {
    return applicationContext.containsBean(name);
  }

  /**
   * 判断以给定名字注册的bean定义是否是一个singleton还是�?个prototype�?
   * 
   * @param name bean名称
   * @return boolean 名称为name的bean示例是否是singleton，或者prototype
   * @throws NoSuchBeanDefinitionException
   *         如果与给定名字相应的bean定义没有被找到，将会抛出�?个异常（NoSuchBeanDefinitionException�?
   */
  public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
    return applicationContext.isSingleton(name);
  }

  /**
   * 返回名称为name的bean的类�?
   * 
   * @param name bean名称
   * @return Class 注册对象的类�?
   * @throws NoSuchBeanDefinitionException
   */
  public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
    return applicationContext.getType(name);
  }

  /**
   * 如果给定的bean名字在bean定义中有别名，则返回这些别名
   * 
   * @param name bean名称
   * @return �?有别�?
   * @throws NoSuchBeanDefinitionException 与给定名字相应的bean定义没有被找�?
   */
  public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
    return applicationContext.getAliases(name);
  }
}
