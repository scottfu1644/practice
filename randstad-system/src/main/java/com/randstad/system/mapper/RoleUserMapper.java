package com.randstad.system.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import com.randstad.system.entity.RoleUser;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Repository
public interface RoleUserMapper {
  /**
   * 按主键查找对象
   **/
  RoleUser findById(Long roleUserId);

  /**
   * 按参数统计记录数
   **/
  int countByParams(Map<String, Object> params);

  /**
   * 按参数查找对象
   **/
  List<RoleUser> findByParams(Map<String, Object> params);

  /**
   * 按参数分页查找对象
   **/
  List<RoleUser> findByParams(Map<String, Object> params, RowBounds bound);

  /**
   * 新增
   **/
  int insert(RoleUser roleUser);

  /**
   * 修改
   **/
  int update(RoleUser roleUser);

  /**
   * 按主键删除
   **/
  int deleteById(Long roleUserId);

  void batchInsert(List<RoleUser> roleUserList);

  void batchDelete(List<RoleUser> roleUserList);

  void deleteByRole(Long roleId);

  void deleteByUser(Long userId);

}
