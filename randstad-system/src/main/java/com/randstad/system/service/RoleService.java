package com.randstad.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.system.entity.Role;
import com.randstad.system.entity.RoleResource;

/**
 * 
 * Function: 角色信息服务. <br>
 * 
 * @author suzu
 */
public interface RoleService {
  /**
   * 按主键查找
   **/
  Role findRoleById(Long roleId);

  /**
   * 按参数分页查找
   **/
  Page<Role> findRolePage(PageContext pageContext);

  /**
   * 按参数查找对象
   **/
  List<Role> findRoleByParams(Map<String, Object> params);

  /**
   * 新增角色
   **/
  void addRole(Role role);

  /**
   * 修改角色
   **/
  void updateRole(Role role);

  /**
   * 按主键删除
   **/
  int deleteRoleById(Long roleId);

  List<Role> findRoleByUserId(Long userId);

  List<Role> findRoleByUsername(String username);

  Set<String> findRoleCodeByUsername(String username);

  void assignUser(Long roleId, List<Long> userIds);

  void assignResource(Long roleId, List<RoleResource> roleResList);
}
