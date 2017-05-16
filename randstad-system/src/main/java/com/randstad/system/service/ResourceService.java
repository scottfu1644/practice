package com.randstad.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.system.entity.Resource;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public interface ResourceService {
  /**
   * 按主键查找
   **/
  Resource findResourceById(Long id);

  /**
   * 按参数分页查找
   **/
  Page<Resource> findResourcePage(PageContext pageContext);

  /**
   * 按参数查找对象
   **/
  List<Resource> findResourceByParams(Map<String, Object> params);

  /**
   * 保存对象，新增或修改由主键是否为空确定
   **/
  void saveResource(Resource resource);

  /**
   * 按主键删除
   **/
  int deleteResourceById(Long id);

  List<Resource> findFunctionByUser(String username);

  Set<String> findFunctionCodeByUser(String username);

  List<Resource> findAllResources();

  List<Resource> buildFunctionTree(List<Resource> functions);
}
