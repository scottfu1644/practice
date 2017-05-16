package com.randstad.common.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL执行结果
 * 
 * @author suzu
 * 
 */
public class SqlResult {
  // 成功失败，1成功，0失败
  public int status;
  // 结果类型，数据集 1 还是只有message 0
  public int resultType;
  // 执行结果信息
  public String message;
  // 更新记录行数
  public int updatedCount = 0;
  // 列名称数字
  public String[] columns = null;
  // 数据集
  public List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();

  public static final int RESULT_TYPE = 1;
  public static final int NONRESULT_TYPE = 0;
  public static final int SUCCESS = 1;
  public static final int FAILURE = 0;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getResultType() {
    return resultType;
  }

  public void setResultType(int resultType) {
    this.resultType = resultType;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getUpdatedCount() {
    return updatedCount;
  }

  public void setUpdatedCount(int updatedCount) {
    this.updatedCount = updatedCount;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public List<Map<String, Object>> getRowList() {
    return rowList;
  }

  public void setRowList(List<Map<String, Object>> rowList) {
    this.rowList = rowList;
  }
}
