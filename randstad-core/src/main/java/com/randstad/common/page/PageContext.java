package com.randstad.common.page;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页信息类
 * 
 * @author suzu
 * 
 */
public class PageContext {
  // -- 公共变量 --//
  public static final String ASC = "asc";

  public static final String DESC = "desc";

  private boolean autoCount = true;

  // 页码，从1开始
  private int pageNo = 1;

  // 每页记录数
  private int pageSize = 10;

  // 第一条记录序号，从0开始，计算产生
  private int offset = 0;

  // 最后一条记录序号，从0开始，计算产生
  // private int lastRecord = 0;

  // 分页参数
  private Map<String, Object> params = new HashMap<String, Object>();

  // 排序条件
  private String sortClause = null;

  public int getPageNo() {
    return pageNo;
  }

  public void setPage(int pageNo) {
    if (pageNo < 1) {
      this.pageNo = 1;
    } else {
      this.pageNo = pageNo;
    }
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    if (pageSize < 1) {
      this.pageSize = 10;
    } else {
      this.pageSize = pageSize;
    }
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
    if (this.offset < 0) {
      this.offset = 0;
    }
  }

  //
  // public int getLastRecord() {
  // return page * pageSize - 1;
  // }

  public Map<String, Object> getParams() {
    return params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
  }

  public String getSortClause() {
    return sortClause;
  }

  public void setSortClause(String sortClause) {
    this.sortClause = sortClause;
  }

  /**
   * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
   */
  public boolean isAutoCount() {
    return autoCount;
  }

  /**
   * 设置查询对象时是否自动先执行count查询获取总记录数.
   */
  public void setAutoCount(final boolean autoCount) {
    this.autoCount = autoCount;
  }


}
