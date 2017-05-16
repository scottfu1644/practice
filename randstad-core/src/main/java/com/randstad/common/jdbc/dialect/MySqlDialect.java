package com.randstad.common.jdbc.dialect;

/**
 * 
 * MySql数据库实现
 * 
 */
public class MySqlDialect implements Dialect {
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    return MySqlPageHepler.getPageSql(sql, offset, limit);
  }
}
