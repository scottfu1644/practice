package com.randstad.mybatis.mapper;

import org.apache.ibatis.session.RowBounds;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * Function: Mybatis公共映射类. <br>
 * 
 * @author suzu
 */
@Repository
// @Component("mybatisCommonMapper") 默认为commonMapper，类名首字母小写
public interface CommonMapper {

  public int insert(String sql);

  public int update(String sql);

  public int delete(String sql);

  public List<Map<String, Object>> query(String sql, RowBounds bounds);

  public Date currentTimestamp();
}
