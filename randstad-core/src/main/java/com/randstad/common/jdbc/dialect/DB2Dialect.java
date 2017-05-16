package com.randstad.common.jdbc.dialect;

/**
 * 
 * DB2数据库实现
 * 
 */
public class DB2Dialect implements Dialect {
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    sql = sql.trim();

    StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
    sqlBuilder.append("select * from (select tmp_page.*,rownumber() over() as row_id from ( ");
    sqlBuilder.append(sql);
    sqlBuilder
        .append(" ) as tmp_page) where row_id between " + offset + " and " + (offset + limit));

    return sqlBuilder.toString();
  }
}
