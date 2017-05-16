package com.randstad.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.common.tree.TreeUtils;
import com.randstad.common.exception.ApplicationException;
import com.randstad.mybatis.Bound;
import com.randstad.system.entity.Operation;
import com.randstad.system.entity.Resource;
import com.randstad.system.mapper.ResourceMapper;
import com.randstad.system.service.ResourceService;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
  private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

  @Autowired
  private ResourceMapper resourceDao;

  /**
   * 
   */
  public Resource findResourceById(Long id) {
    Resource resource = null;
    try {
      resource = resourceDao.findById(id);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{id=" + id + "}", e);
      throw new ApplicationException("查询失败，\n错误信息：" + e.getMessage());
    }
    return resource;
  }

  /**
   * 
   */
  public Page<Resource> findResourcePage(PageContext pageContext) {
    Map<String, Object> params = new HashMap<String, Object>();

    // pre process parameters
    if (pageContext != null) {
      params.putAll(pageContext.getParams());
      // params.put();

      reviseParams(params);
    }
    try {
      Bound bound = new Bound(pageContext.getOffset(), pageContext.getPageSize());
      int count = resourceDao.countByParams(params);
      List<Resource> list = new ArrayList<Resource>();
      list = resourceDao.findByParams(params, bound);
      return new Page<Resource>(count, pageContext.getPageNo(), pageContext.getPageSize(), list);
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
  public List<Resource> findResourceByParams(Map<String, Object> params) {
    List<Resource> list = new ArrayList<Resource>();
    // pre process parameters
    if (params != null) {
      // params.put();

      reviseParams(params);
    }
    try {
      list = resourceDao.findByParams(params);
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
   * 保存资源.
   * 
   * @param resource 资源
   */
  public void saveResource(Resource resource) {
    Long id = resource.getId();
    try {
      if (id == null) {
        resourceDao.insert(resource);
      } else {
        Resource old = resourceDao.findById(id);
        // set Object property
        // old.setId(resource.getId());
        old.setResCode(resource.getResCode());
        old.setResType(resource.getResType());
        old.setResName(resource.getResName());
        old.setResDesc(resource.getResDesc());
        old.setParentId(resource.getParentId());
        old.setResStatus(resource.getResStatus());
        old.setResModule(resource.getResModule());
        old.setResUrl(resource.getResUrl());
        old.setResIcon(resource.getResIcon());
        old.setOrderNo(resource.getOrderNo());
        old.setOperations(resource.getOperations());

        resourceDao.update(old);
      }
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("对象{entity=" + resource + "}", e);
      throw new ApplicationException("保存失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * delete resource by resourcid.
   * 
   * @param id resource id for delete
   * @return int deleted rows
   */
  public int deleteResourceById(Long id) {
    try {
      return resourceDao.deleteById(id);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("{id=" + id + "}", e);
      throw new ApplicationException("删除失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   */
  public Set<String> getFunctionCodeSet(Set<String> roleCodes) {
    return null;
  }

  /**
   * find all resources.
   * 
   * @return 资源列表
   */
  public List<Resource> findAllResources() {
    List<Resource> list = new ArrayList<Resource>();
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("resStatus", 1);
      list = resourceDao.findByParams(params, null);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new ApplicationException("查询失败", e);
    }
    return list;
  }

  /**
   * 
   * find resource by userid.
   * 
   * @param userId user id
   * @return list resource list of user
   */
  public List<Resource> findResourcesByUser(Long userId) {
    List<Resource> list = new ArrayList<Resource>();
    try {
      list = resourceDao.findByUser(userId);

      // 合并按钮权限
      list = mergeAssignedOperations(list);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new ApplicationException("按用户查询失败，查询条件{User=" + userId + "}", e);
    }
    return list;
  }

  public List<Resource> findFunctionByUser(String username) {
    List<Resource> list = new ArrayList<Resource>();
    try {
      list = resourceDao.findByUser(username);

      // 合并按钮权限
      list = mergeAssignedOperations(list);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new ApplicationException("按用户查询失败，查询条件{User=" + username + "}", e);
    }
    return list;
  }

  /**
   * 查询用户权限Permissions
   * <p>
   * <p>格式为：USER, USER:VIEW, USER:ADD......
   * 
   * @param username 用户名 
   * @return 权限集合
   */
  public Set<String> findFunctionCodeByUser(String username) {
    List<Resource> funcList = findFunctionByUser(username);
    Set<String> functionCodes = new HashSet<>();
    for (Resource function : funcList) {
      String resCode = function.getResCode();
      functionCodes.add(resCode);
      List<Operation> opts = function.getAsssignedOpts();
      for (Operation operation : opts) {
        functionCodes.add(resCode + ":" + operation.getCode());
      }
    }
    return functionCodes;
  }

  /**
   * 
   * find resource by role id.
   * 
   * @param roleId role id
   * @return list resource list of role
   */
  public List<Resource> findResourcesByRole(Long roleId) {
    List<Resource> list = new ArrayList<Resource>();
    try {
      list = resourceDao.findByRole(roleId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      throw new ApplicationException("按角色查询失败，查询条件{Role=" + roleId + "}", e);
    }
    return list;
  }

  /**
   * 
   * 已授权菜单的操作项去重.
   * 
   * @param resources
   * @return
   */
  private List<Resource> mergeAssignedOperations(List<Resource> resources) {
    List<Resource> tmplist = new ArrayList<Resource>();
    Map<Long, Resource> map = new LinkedHashMap<Long, Resource>();
    for (Resource resource : resources) {
      Long resId = resource.id();
      Resource func = map.get(resId);
      if (func == null) {
        map.put(resId, resource);
        continue;
      }

      List<Operation> opts = func.getAsssignedOpts();
      for (Operation operation : resource.getAsssignedOpts()) {
        if (!opts.contains(operation)) {
          opts.add(operation);
        }
      }
    }

    tmplist.addAll(map.values());
    return tmplist;
  }

  /**
   * 构建功能树结构
   * 
   * @param resources 功能列表 
   * @return 功能树
   */
  public List<Resource> buildFunctionTree(List<Resource> resources) {
    List<Resource> nodes = TreeUtils.buildTree(resources);
    return nodes;
  }

}
