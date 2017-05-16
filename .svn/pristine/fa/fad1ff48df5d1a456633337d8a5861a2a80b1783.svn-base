package com.randstad.mybatis;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;

/**
 * ClassName: MyBatisDao. <br>
 * Function: MyBatis基础DAO类，提供SqlSession的注入方�?. <br>
 * Date: 2014-1-23 下午10:44:26 <br>
 * 
 * 
 * @author Walker mailto: zuwei.su@qq.com 2014-1-23 下午10:44:26
 */
public class MybatisDao {
  protected SqlSessionTemplate sqlSession;
  protected String namespace = null;

  public MybatisDao() {}

  @Resource
  public void setSqlSession(SqlSessionTemplate sqlSession) {
    this.sqlSession = sqlSession;
  }

  protected String fullSqlMapName(String sqlMapId) {
    return namespace + "." + sqlMapId;
  }

}
