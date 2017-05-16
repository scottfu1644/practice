package com.randstad.common.jdbc.dialect;

/**
 * 
 * HSql数据库实现
 * 
 */
public class HSqlDialect implements Dialect {
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    sql = sql.trim();

    StringBuilder sqlBuilder = new StringBuilder(sql.length() + 20);
    sqlBuilder.append(sql);
    sqlBuilder.append(" limit " + limit + " offset " + offset);

    return sqlBuilder.toString();
  }
}
