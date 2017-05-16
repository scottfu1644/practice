package com.randstad.system.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.randstad.system.entity.Org;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Repository
public interface OrgMapper {
  /**
   * 按主键查找对象
   **/
  Org findById(Long id);

  /**
   * 按参数统计记录数
   **/
  int countByParams(Map<String, Object> params);

  /**
   * 按参数查找对象
   **/
  List<Org> findByParams(Map<String, Object> params);

  /**
   * 按参数分页查找对象
   **/
  List<Org> findByParams(Map<String, Object> params, RowBounds bound);

  /**
   * 新增
   **/
  int insert(Org org);

  /**
   * 修改
   **/
  int update(Org org);

  /**
   * 按主键删除
   **/
  int deleteById(Long orgId);

}
