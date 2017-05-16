package com.randstad.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Function: 树节点. <br>
 * 
 * @author suzu
 */
public class SimpleTreeNode implements TreeNode<SimpleTreeNode> {

  private Long nodeId = null;

  private Long parentId = null;

  private String name = null;

  private SimpleTreeNode parent = null;

  private List<SimpleTreeNode> children = new ArrayList<SimpleTreeNode>();

  @SuppressWarnings("unchecked")
  public Long id() {
    return nodeId;
  }

  public void setNodeId(Long nodeId) {
    this.nodeId = nodeId;
  }

  @SuppressWarnings("unchecked")
  public Long parentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  // @Override
  // public String getName() {
  // return name;
  // }

  public void setName(String name) {
    this.name = name;
  }

  // @Override
  // public SimpleTreeNode getParent() {
  // return parent;
  // }

  public void parent(SimpleTreeNode parent) {
    this.parent = parent;
  }

  public List<SimpleTreeNode> children() {
    return children;
  }

}
