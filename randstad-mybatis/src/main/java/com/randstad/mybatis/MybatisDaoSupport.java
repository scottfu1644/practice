package com.randstad.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 
 * 
 * @author suzu
 * 
 * @param <T>
 */
/**
 * 
 * ClassName: MybatisDaoSupport <br>
 * Function: Mybatis 的 DAO基类，基于Spring DaoSupport类 <br>
 * 解决mybatis-spring-1.2.0 中不自动装载SqlSeesion的问题 <br>
 * 当你使用1.2.0的jar包时候，mybatis-spring的这个基类不会再直接将spring配置文件中配好的 sqlSessionFactory <br>
 * 或者 sqlSessionTemplate注入到这个基类中，而在使用时候，SqlSessionDaoSupport的方法 <br>
 * Date: 2014-1-23 下午10:49:21 <br>
 * 
 * @author Walker.Su(苏祖�?) mailto: zuwei.su@qq.com 2014-1-23 下午10:49:21
 */
public abstract class MybatisDaoSupport extends SqlSessionDaoSupport {
  @Autowired
  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    super.setSqlSessionTemplate(sqlSessionTemplate);
  }

  protected <S> S getMapper(Class<S> clazz) {
    return getSqlSession().getMapper(clazz);
  }
}
