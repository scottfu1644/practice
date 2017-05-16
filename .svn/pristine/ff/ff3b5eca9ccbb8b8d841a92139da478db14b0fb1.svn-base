package com.randstad.system.mapper;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import com.randstad.system.entity.Resource;
import com.randstad.system.entity.RoleUser;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Repository
public interface ResourceMapper {
  /**
   * 按主键查找对象
   **/
  Resource findById(Long id);

  /**
   * 按参数统计记录数
   **/
  int countByParams(Map<String, Object> params);

  /**
   * 按参数查找对象
   **/
  List<Resource> findByParams(Map<String, Object> params);

  /**
   * 按参数分页查找对象
   **/
  List<Resource> findByParams(Map<String, Object> params, RowBounds bound);

  /**
   * 新增
   **/
  int insert(Resource resource);

  /**
   * 修改
   **/
  int update(Resource resource);

  /**
   * 按主键删除
   **/
  int deleteById(Long id);

  List<Resource> findByUser(Long userId);

  List<Resource> findByUser(String username);

  List<Resource> findByRole(Long roleId);

  void deleteByUser(Long userId);

  void batchInsert(List<RoleUser> roleUsers);

}
