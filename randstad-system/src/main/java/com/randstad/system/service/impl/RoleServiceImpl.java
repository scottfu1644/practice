package com.randstad.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.randstad.common.exception.ApplicationException;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.mybatis.Bound;

import com.randstad.system.entity.Role;
import com.randstad.system.entity.RoleResource;
import com.randstad.system.entity.RoleUser;
import com.randstad.system.mapper.RoleMapper;
import com.randstad.system.mapper.RoleResourceMapper;
import com.randstad.system.mapper.RoleUserMapper;
// import com.randstad.system.mapper.RoleUserMapper;
import com.randstad.system.service.RoleService;

/**
 * Function: 角色服务. <br>
 * 
 * @author suzu
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
  private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

  @Autowired
  private RoleMapper roleDao;

  @Autowired
  private RoleUserMapper roleUserDao;

  @Autowired
  private RoleResourceMapper roleResourceDao;

  /**
   * 
   */
  public Role findRoleById(Long roleId) {
    Role role = null;
    try {
      role = roleDao.findById(roleId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{roleId=" + roleId + "}", e);
      throw new ApplicationException("查询失败，\n错误信息：" + e.getMessage());
    }
    return role;
  }

  /**
   * 
   */
  public Page<Role> findRolePage(PageContext pageContext) {
    Map<String, Object> params = new HashMap<String, Object>();

    // pre process parameters
    if (pageContext != null) {
      params.putAll(pageContext.getParams());
      // params.put();

      reviseParams(params);
    }
    try {
      Bound bound = new Bound(pageContext.getOffset(), pageContext.getPageSize());
      int count = roleDao.countByParams(params);
      List<Role> list = new ArrayList<Role>();
      list = roleDao.findByParams(params, bound);
      return new Page<Role>(count, pageContext.getPageNo(), pageContext.getPageSize(), list);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{pageContext=" + pageContext + "}", e);
      throw new ApplicationException("分页查询失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   */
  public List<Role> findRoleByParams(Map<String, Object> params) {
    List<Role> list = new ArrayList<Role>();
    // pre process parameters
    if (params != null) {
      // params.put();

      reviseParams(params);
    }
    try {
      list = roleDao.findByParams(params);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{params=" + params + "}", e);
      throw new ApplicationException("查询失败，错误信息：" + e.getMessage(), e);
    }
    return list;
  }

  /**
   * reviseParams: 修正按参数查找方法的参数信息.<br>
   * 
   * @param params
   * 
   */
  private void reviseParams(Map<String, Object> params) {
    /*
     * String cityName = (String)params.get("cityName"); if (StringUtils.isNotBlank(cityName))
     * params.put( "cityName", "%" + cityName.toLowerCase().trim() + "%");
     */
  }

  /**
   * 新增角色
   */
  public void addRole(Role role) {
    roleDao.insert(role);
  }

  /**
   * 修改角色
   */
  public void updateRole(Role role) {
    Long roleId = role.getId();
    try {
      Role old = roleDao.findById(roleId);
      // set Object property
      // old.setRoleId(role.getRoleId());
      old.setRoleCode(role.getRoleCode());
      old.setRoleName(role.getRoleName());
      old.setRoleDesc(role.getRoleDesc());
      old.setRoleStatus(role.getRoleStatus());
      old.setWelcomeUrl(role.getWelcomeUrl());
      old.setOrderNo(role.getOrderNo());
      old.setCreateBy(role.getCreateBy());
      old.setCreateDate(role.getCreateDate());
      old.setUpdateBy(role.getUpdateBy());
      old.setUpdateDate(role.getUpdateDate());

      roleDao.update(old);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("对象{entity=" + role + "}", e);
      throw new ApplicationException("修改角色失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 删除角色
   */
  public int deleteRoleById(Long roleId) {
    try {
      return roleDao.deleteById(roleId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("{roleId=" + roleId + "}", e);
      throw new ApplicationException("删除失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   */
  @Override
  public List<Role> findRoleByUserId(Long userId) {
    List<Role> roles = roleDao.findByUserId(userId);

    return roles;
  }

  /**
   * 
   * 查询用户被授予的角色.
   * 
   * @param username 用户登录名
   * @return 用户关联的角色
   */
  @Override
  public List<Role> findRoleByUsername(String username) {
    List<Role> roles = roleDao.findByUserName(username);

    return roles;
  }

  @Override
  public Set<String> findRoleCodeByUsername(String username) {
    List<Role> roles = roleDao.findByUserName(username);
    Set<String> codeSet = new HashSet<>();
    for (Role role : roles) {
      codeSet.add(role.getRoleCode());
    }
    return codeSet;
  }

  public void assignUser(Long roleId, List<Long> userIds) {
    roleUserDao.deleteByRole(roleId);

    List<RoleUser> roleUserList = new ArrayList<>();
    for (Long userId : userIds) {
      RoleUser roleUser = new RoleUser();
      roleUser.setRoleId(roleId);
      roleUser.setUserId(userId);

      roleUserList.add(roleUser);
    }

    roleUserDao.batchInsert(roleUserList);
  }

  public void assignResource(Long roleId, List<RoleResource> roleResList) {
    roleResourceDao.deleteByRole(roleId);

    for (RoleResource roleRes : roleResList) {
      roleRes.setRoleId(roleId);
      // roleRes.setResId(resId);
      // roleRes.setOperations(operations);
    }

    roleResourceDao.batchInsert(roleResList);
  }
}
