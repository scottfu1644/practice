/**
 * 修改历史：<br>
 * =================================================================<br>
 * 修改人 修改时间 修改原因/内容<br>
 * =================================================================<br>
 * sam 20100111 增加修改历史注释<br>
 */

package com.randstad.common.jdbc.dialect;

/**
 * 基于SQLServer2005的SQL分页实现
 * 
 * @author badqiu(rapid-framework作者)
 * 
 */
// Hibernate BUG:
// http://opensource.atlassian.com/projects/hibernate/browse/HHH-2655
//
public class SqlServer2005Dialect implements Dialect {
  public boolean supportPaginal() {
    return true;
  }

  /**
   * Add a LIMIT clause to the given SQL SELECT
   * 
   * The LIMIT SQL will look like:
   * 
   * WITH query AS (SELECT TOP 100 percent ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as
   * __row_number__, * from table_name) SELECT * FROM query WHERE __row_number__ BETWEEN :offset and
   * :lastRows ORDER BY __row_number__
   * 
   * @param sql The SQL statement to base the limit query off of.
   * @param offset Offset of the first row to be returned by the query (zero-based)
   * @param limit Maximum number of rows to be returned by the query
   * @return A new SQL statement with the LIMIT clause applied.
   */
  public String getPaginationSql(String sql, int offset, int limit) {
    StringBuffer pagingBuilder = new StringBuffer();
    String orderby = getOrderByPart(sql);
    String distinctStr = "";

    String loweredString = sql.toLowerCase();
    String sqlPartString = sql;
    if (loweredString.trim().startsWith("select")) {
      int index = 6;
      if (loweredString.startsWith("select distinct")) {
        distinctStr = "DISTINCT ";
        index = 15;
      }
      sqlPartString = sqlPartString.substring(index);
    }
    pagingBuilder.append(sqlPartString);

    // if no ORDER BY is specified use fake ORDER BY field to avoid errors
    if (orderby == null || orderby.length() == 0) {
      orderby = "ORDER BY CURRENT_TIMESTAMP";
    }

    StringBuffer result = new StringBuffer();
    result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
        .append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ")
        .append(pagingBuilder).append(") SELECT * FROM query WHERE __row_number__ BETWEEN ")
        .append(offset).append(" AND ").append(offset + limit).append(" ORDER BY __row_number__");

    return result.toString();
  }

  private String getOrderByPart(String sql) {

    String loweredString = sql.toLowerCase();
    int orderByIndex = loweredString.indexOf("order by");
    if (orderByIndex != -1) {
      // if we find a new "order by" then we need to ignore
      // the previous one since it was probably used for a subquery
      return sql.substring(orderByIndex);
    } else {
      return "";
    }
  }
}
