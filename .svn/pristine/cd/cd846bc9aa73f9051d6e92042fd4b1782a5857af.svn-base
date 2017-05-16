package com.randstad.common.tree;

import java.util.List;

/**
 * Function: 树节点接口. <br>
 * 
 * @author suzu
 * @param <T> 节点包含的对象
 */
public interface TreeNode<T extends TreeNode<T>> {

  public <S extends Object> S id();

  public <S extends Object> S parentId();

  // public String getName();

  // public T getParent();

  public void parent(T parent);

  public List<T> children();

}
