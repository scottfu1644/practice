/**
 * 修改历史：<br>
 * =================================================================<br>
 * 修改人 修改时间 修改原因/内容<br>
 * =================================================================<br>
 * sam 20100111 增加修改历史注释<br>
 */

package com.randstad.common.jdbc.dialect;

/**
 * 基于Derby的SQL分页实现
 * 
 * @author Sam
 * 
 */
public class DerbyDialect implements Dialect {
  public boolean supportPaginal() {
    return false;
  }

  public String getPaginationSql(String sql, int offset, int limit) {
    throw new UnsupportedOperationException("paged queries not supported");
  }

}
