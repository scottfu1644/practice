package com.randstad.system.service;

import java.util.List;
import java.util.Map;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;
import com.randstad.system.entity.Org;

/**
 * 
 * Function: 组织服务. <br>
 * 
 * @author suzu
 */
public interface OrgService {
  /**
   * 按主键查找
   **/
  Org findOrgById(Long orgId);

  /**
   * 按参数分页查找
   **/
  Page<Org> findOrgPage(PageContext pageContext);

  /**
   * 按参数查找对象
   **/
  List<Org> findOrgByParams(Map<String, Object> params);

  /**
   * 保存对象，新增或修改由主键是否为空确定
   **/
  void addOrg(Org org);

  /**
   * 保存对象，新增或修改由主键是否为空确定
   **/
  void updateOrg(Org org);

  /**
   * 按主键删除
   **/
  int deleteOrgById(Long orgId);
}
