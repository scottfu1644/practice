package com.randstad.common.jdbc.dialect;

/**
 * @author xdwang
 * 
 *         2012-12-19 下午7:50:44
 * 
 *         MySQL数据库实现
 * 
 */
public class MySql5Dialect implements Dialect {
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    return MySql5PageHepler.getPageSql(sql, offset, limit);
  }
}
