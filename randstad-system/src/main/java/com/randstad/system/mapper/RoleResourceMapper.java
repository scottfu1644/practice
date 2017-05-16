package com.randstad.system.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.randstad.system.entity.RoleResource;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Repository
public interface RoleResourceMapper {
  /**
   * 按主键查找对象
   **/
  RoleResource findById(Long roleResId);

  /**
   * 按参数统计记录数
   **/
  int countByParams(Map<String, Object> params);

  /**
   * 按参数查找对象
   **/
  List<RoleResource> findByParams(Map<String, Object> params);

  /**
   * 按参数分页查找对象
   **/
  List<RoleResource> findByParams(Map<String, Object> params, RowBounds bound);

  /**
   * 新增
   **/
  int insert(RoleResource roleResource);

  /**
   * 修改
   **/
  int update(RoleResource roleResource);

  /**
   * 按主键删除
   **/
  int deleteById(Long roleResId);

  /**
   * 按主键删除
   **/
  int batchInsert(List<RoleResource> list);

  /**
   * 按主键删除
   **/
  int deleteByRole(Long roleId);

}
