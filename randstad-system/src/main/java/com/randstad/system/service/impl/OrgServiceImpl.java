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

import com.randstad.system.entity.Org;
import com.randstad.system.mapper.OrgMapper;
import com.randstad.system.service.OrgService;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {
  private static final Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);

  @Autowired
  private OrgMapper orgDao;

  /**
   * 
   */
  public Org findOrgById(Long orgId) {
    Org org = null;
    try {
      org = orgDao.findById(orgId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("查询条件{orgId=" + orgId + "}", e);
      throw new ApplicationException("查询失败，\n错误信息：" + e.getMessage());
    }
    return org;
  }

  /**
   * 
   */
  public Page<Org> findOrgPage(PageContext pageContext) {
    Map<String, Object> params = new HashMap<String, Object>();

    // pre process parameters
    if (pageContext != null) {
      params.putAll(pageContext.getParams());
      // params.put();

      reviseParams(params);
    }
    try {
      Bound bound = new Bound(pageContext.getOffset(), pageContext.getPageSize());
      int count = orgDao.countByParams(params);
      List<Org> list = new ArrayList<Org>();
      list = orgDao.findByParams(params, bound);
      return new Page<Org>(count, pageContext.getPageNo(), pageContext.getPageSize(), list);
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
  public List<Org> findOrgByParams(Map<String, Object> params) {
    List<Org> list = new ArrayList<Org>();
    // pre process parameters
    if (params != null) {
      // params.put();

      reviseParams(params);
    }
    try {
      list = orgDao.findByParams(params);
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
  public void addOrg(Org org) {
    Long orgId = org.getId();
    try {
      orgDao.insert(org);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("对象{entity=" + org + "}", e);
      throw new ApplicationException("新增失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   */
  public void updateOrg(Org org) {
    Long orgId = org.getId();
    try {
      if (orgId == null) {
        throw new ApplicationException("更新失败，组织ID为空");
      } else {
        Org old = orgDao.findById(orgId);
        if (old == null) {
          throw new ApplicationException("更新失败，对象不存在，无法更新！");
        }
        // set Object property
        // old.setOrgId(org.getOrgId());
        old.setOrgCode(org.getOrgCode());
        old.setOrgName(org.getOrgName());
        old.setParentId(org.getParentId());
        old.setOrgLevel(org.getOrgLevel());
        old.setOrgPath(org.getOrgPath());
        old.setOrgStatus(org.getOrgStatus());
        old.setOrderNo(org.getOrderNo());
        old.setCreateBy(org.getCreateBy());
        old.setCreateDate(org.getCreateDate());
        old.setUpdateBy(org.getUpdateBy());
        old.setUpdateDate(org.getUpdateDate());

        orgDao.update(old);
      }
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("对象{entity=" + org + "}", e);
      throw new ApplicationException("保存失败，错误信息：" + e.getMessage());
    }
  }

  /**
   * 
   */
  public int deleteOrgById(Long orgId) {
    try {
      return orgDao.deleteById(orgId);
    } catch (ApplicationException e) {
      throw e;
    } catch (Exception e) {
      logger.error("{orgId=" + orgId + "}", e);
      throw new ApplicationException("删除失败，错误信息：" + e.getMessage());
    }
  }
}
