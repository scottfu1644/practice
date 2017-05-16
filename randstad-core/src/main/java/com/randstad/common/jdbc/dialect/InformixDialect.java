package com.randstad.common.jdbc.dialect;

/**
 * 
 * Informix数据库实现
 * 
 */
public class InformixDialect implements Dialect {
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    sql = sql.trim();

    StringBuilder sqlBuilder = new StringBuilder(sql.length() + 40);
    sqlBuilder.append("select skip " + offset + " first " + limit + " * from ( ");
    sqlBuilder.append(sql);
    sqlBuilder.append(" ) temp_t");

    return sqlBuilder.toString();
  }
}
