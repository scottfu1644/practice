package com.randstad.common.jdbc.dialect;

/**
 * Take note that at least one column needs to be defined for ORDER BY in oder for OFFSET .. ROWS to
 * work
 */
public class SqlServer2012Dialect implements Dialect {

  public boolean supportPaginal() {
    return true;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    // 必须以order by 子句结尾
    StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
    sqlBuilder.append(sql);
    sqlBuilder.append(" OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY");

    return sqlBuilder.toString();
  }
}
