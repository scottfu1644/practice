package com.randstad.common.jdbc.dialect;

/*
 * 
 * 
 */
/**
 * 
 * Function: SQLServer数据库实现. <br>
 * 
 * @author suzu
 */
public class SqlServerDialect implements Dialect {

  SqlServer sqlServer = new SqlServer();

  public boolean supportPaginal() {
    return true;
  }

  /**
   * 将sqlserver查询语句转换为分页语句<br>
   * 注意事项：<br>
   * <ol>
   * <li>请先保证你的SQL可以执行</li>
   * <li>sql中最好直接包含order by，可以自动从sql提取</li>
   * <li>如果没有order by，可以通过入参提供，但是需要自己保证正确</li>
   * <li>如果sql有order by，可以通过orderby参数覆盖sql中的order by</li>
   * <li>order by的列名不能使用别名</li>
   * <li>表和列使用别名的时候不要使用单引号(')</li>
   * </ol>
   * 该类设计为一个独立的工具类，依赖jsqlparser,可以独立使用
   * 
   * @author liuzh
   */
  public String getPaginationSql(String sql, int offset, int limit) {
    String tmpSql = sql;
    try {
      tmpSql = sqlServer.convertToPageSql(sql, offset, limit);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return tmpSql;
  }
}
