package com.randstad.common.jdbc.dialect;

/**
 * 
 * 数据库方言接口
 * 
 */

public interface Dialect {

  /**
   * Function: TODO(suzu) Add function Description. <br>
   * 
   * @author suzu
   */
  public static enum Type {
    mysql("mysql"), h2("h2"), hsql("hsql"), postgresql("postgresql"), db2("db2"), informix(
        "informix"), sqlserver("sqlserver"), sqlserver2012("sqlserver2012"), oracle("oracle");

    // 私有成员变量，保存名称
    private String value;

    public String getValue() {
      return value;
    }

    // 带参构造函数
    Type(String value) {
      this.value = value;
    }
  }

  /**
   * 是否支持分页
   * 
   * @return
   */
  public boolean supportPaginal();

  /**
   * @descrption 获取分页SQL
   * @author xdwang 2012-12-19下午7:48:44
   * @param sql 原始查询SQL
   * @param offset 开始记录索引（从零开始）
   * @param limit 每页记录大小
   * @return 返回数据库相关的分页SQL语句
   */
  public abstract String getPaginationSql(String sql, int offset, int limit);

}
