/**
 * 修改历史：<br>
 * =================================================================<br>
 * 修改人 修改时间 修改原因/内容<br>
 * =================================================================<br>
 * sam 20100111 增加修改历史注释<br>
 */

package com.randstad.common.jdbc.dialect;

/**
 * 基于SybaseDialect的SQL分页实现
 * 
 * @author Sam
 * 
 */
public class SybaseDialect implements Dialect {
  public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit,
      String limitPlaceholder) {

    throw new UnsupportedOperationException("paged queries not supported");
  }

  public boolean supportPaginal() {
    return false;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    throw new UnsupportedOperationException("paged queries not supported");
  }
}
