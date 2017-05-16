package com.randstad.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.randstad.common.exception.ApplicationException;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.mybatis.Bound;
import com.randstad.system.entity.RoleUser;
import com.randstad.system.entity.User;
import com.randstad.system.mapper.ResourceMapper;
import com.randstad.system.mapper.UserMapper;
import com.randstad.system.service.UserService;

/**
 * 
 * Function: 用户管理服务. <br>
 * 
 * @author suzu
 */
@Service("userService")
public class UserServiceImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserMapper userDao;

  @Autowired
  private ResourceMapper roleuserDao;

  /**
   * 
   */
  public User findUserById(Long userId) {
    User user = null;
    try {
      user = userDao.findById(userId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{userId=" + userId + "}", e);
      throw new ApplicationException("查询失败，\n错误信息：" + e.getMessage());
    }
    return user;
  }

  public Page<User> findUserPage(PageContext pageContext) {
    Map<String, Object> params = new HashMap<String, Object>();

    // pre process parameters
    if (pageContext != null) {
      params.putAll(pageContext.getParams());
      // params.put();

      reviseParams(params);
    }
    try {
      Bound bound = new Bound(pageContext.getOffset(), pageContext.getPageSize());
      int count = userDao.countByParams(params);
      List<User> list = new ArrayList<User>();
      list = userDao.findByParams(params, bound);
      return new Page<User>(count, pageContext.getPageNo(), pageContext.getPageSize(), list);
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
  public List<User> findUserByParams(Map<String, Object> params) {
    List<User> list = new ArrayList<User>();
    // pre process parameters
    if (params != null) {
      // params.put();

      reviseParams(params);
    }
    try {
      list = userDao.findByParams(params);
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
   * 
   */
  public void saveUser(User user) {
    Long userId = user.getId();
    try {
      if (userId == null) {
        userDao.insert(user);
      } else {
        User old = userDao.findById(userId);
        // set Object property
        // old.setUserId(user.getUserId());
        old.setUserName(user.getUserName());
        old.setUserPassword(user.getUserPassword());
        old.setUserFullName(user.getUserFullName());
        old.setGender(user.getGender());
        old.setEmail(user.getEmail());
        old.setMobile(user.getMobile());
        old.setTelephone(user.getTelephone());
        old.setOrgId(user.getOrgId());
        old.setUserStatus(user.getUserStatus());
        old.setOrderNo(user.getOrderNo());
        old.setRemark(user.getRemark());
        old.setCreateBy(user.getCreateBy());
        old.setCreateDate(user.getCreateDate());
        old.setUpdateBy(user.getUpdateBy());
        old.setUpdateDate(user.getUpdateDate());

        userDao.update(old);
      }
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("对象{entity=" + user + "}", e);
      throw new ApplicationException("保存失败，错误信息：" + e.getMessage());
    }
  }

  public int deleteUserById(Long userId) {
    try {
      return userDao.deleteById(userId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("{userId=" + userId + "}", e);
      throw new ApplicationException("删除失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   * 用户分配角色.
   * 
   * @param userId
   * @param roleIds
   */
  public void assignRole(Long userId, List<Long> roleIds) {
    // 先删除
    roleuserDao.deleteByUser(userId);

    List<RoleUser> roleUsers = new ArrayList<RoleUser>();
    for (Long roleId : roleIds) {
      RoleUser roleUser = new RoleUser();
      roleUser.setRoleId(roleId);
      roleUser.setUserId(userId);

      roleUsers.add(roleUser);
    }
    roleuserDao.batchInsert(roleUsers);
  }

  /**
   * 
   * 查询登录名对应的用户.
   * 
   * @param userName user name
   * @return User user detail info
   * @throws ApplicationException exception
   */
  public User findUserByLoginName(String userName) throws ApplicationException {
    User user = null;
    try {
      user = userDao.selectByUserName(userName);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new ApplicationException("", e);
    }
    return user;
  }

  public void addUser(User user) {
    userDao.insert(user);
  }

  public void updateUser(User user) {
    userDao.update(user);
  }

  public List<User> findAllUser() {
    List<User> allUser = userDao.findByParams(null);
    return allUser;
  }

  public List<User> findAllActiveUser() {
    Map<String, Object> params = new HashMap<>();
    params.put("userStatus", "1");
    List<User> allUser = userDao.findByParams(params);
    return allUser;
  }

  @Override
  public List<User> findUserByOrgId(Long orgId) {
    Map<String, Object> params = new HashMap<>();
    params.put("orgId", orgId);
    params.put("userStatus", "1");
    List<User> allUser = userDao.findByParams(params);
    return allUser;
  }
}
