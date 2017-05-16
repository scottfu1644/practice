package com.randstad.system.service;

import java.util.List;
import java.util.Map;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.system.entity.User;

/**
 * 
 * Function: 用户信息服务. <br>
 * 
 * @author suzu
 */
public interface UserService {
  /**
   * 按主键查找
   **/
  User findUserById(Long userId);

  /**
   * 按参数分页查找
   **/
  Page<User> findUserPage(PageContext pageContext);

  /**
   * 按参数查找对象
   **/
  List<User> findUserByParams(Map<String, Object> params);

  /**
   * 新增用户
   **/
  void addUser(User user);

  /**
   * 修改用户
   **/
  void updateUser(User user);

  /**
   * 按主键删除
   **/
  int deleteUserById(Long userId);

  void assignRole(Long userId, List<Long> roleIds);

  User findUserByLoginName(String username);

  List<User> findAllUser();

  List<User> findAllActiveUser();

  List<User> findUserByOrgId(Long orgId);
}
