package com.randstad.common.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Function: 树结构工具类. <br>
 * 
 * @author suzu
 */
public class TreeUtils {

  private static Logger logger = LoggerFactory.getLogger(TreeUtils.class);

  /**
   * 构造业务类型树模型，返回顶层节点列表。<br>
   * . 此算法存在一种情况：<br>
   * 如果一个节点有parentId，但在列表list中没有parentId对应的数据，说明数据是不完整的。<br>
   * 目前是将这个节点作为顶层节点返回。<br>
   * 
   * @param list dd
   * @return list 顶部节点列表
   */
  public static <T extends TreeNode<T>> List<T> buildTree(List<T> list) {
    List<T> rootList = new ArrayList<T>();

    Map<Object, T> map = new HashMap<Object, T>();
    for (T tnode : list) {
      map.put(tnode.id(), tnode);
    }

    for (T node : list) {
      // Number nodeId = tnode.getNodeId();
      Object parentId = node.parentId();
      // String name = tnode.getName();

      if (parentId == null) {
        rootList.add((T) node);
      } else {
        T parent = map.get(parentId);
        if (parent == null) {
          logger.error("Can't found parent node with id : " + parentId);

          // 找不到父节点的节点放到顶层节点列表中
          rootList.add((T) node);
        } else {
          parent.children().add(node);
          node.parent(parent);
        }
      }
    }

    return rootList;
  }
}
