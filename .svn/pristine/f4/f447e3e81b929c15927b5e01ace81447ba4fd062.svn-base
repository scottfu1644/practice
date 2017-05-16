package com.randstad.system.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.randstad.system.entity.Role;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Repository
public interface RoleMapper {
  /**
   * 按主键查找对象
   **/
  Role findById(Long roleId);

  /**
   * 按参数统计记录数
   **/
  int countByParams(Map<String, Object> params);

  /**
   * 按参数查找对象
   **/
  List<Role> findByParams(Map<String, Object> params);

  /**
   * 按参数分页查找对象
   **/
  List<Role> findByParams(Map<String, Object> params, RowBounds bound);

  /**
   * 新增
   **/
  int insert(Role role);

  /**
   * 修改
   **/
  int update(Role role);

  /**
   * 按主键删除
   **/
  int deleteById(Long roleId);

  List<Role> findByUserId(Long userId);

  List<Role> findByUserName(String userName);

}
