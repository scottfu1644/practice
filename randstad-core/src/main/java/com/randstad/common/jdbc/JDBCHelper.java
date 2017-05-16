package com.randstad.common.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// import javax.sql.DataSource;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;

/**
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class JDBCHelper {
  private static Logger logger = LoggerFactory.getLogger(JDBCHelper.class);

  private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

  // private static JdbcBaseDao dao = new JdbcBaseDao();

  // private static final String sequenceSql = "select ?.nextVal from dual";

  private static final String sequenceSql =
      "SELECT Auto_increment FROM information_schema.tables WHERE table_name=?";

  public static Connection getConnection() {
    // System.setProperty( "jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
    Connection con = null;
    // try {
    // DataSource ds = null; // (DataSource) SpringContextUtil.getBean("dataSource");
    // // ds = new ComboPooledDataSource("userApp"); // 从c3p0数据库连接池
    //
    // con = threadLocal.get();
    // if (con == null) {
    // con = ds.getConnection();
    // threadLocal.set(con);
    // }
    //
    // // con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "pms", "12345");
    // // con = DriverManager.getConnection("jdbc:oracle:thin:@10.204.7.67:1521:DACR", "erp",
    // // "erpadmin");
    // con.setAutoCommit(false);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    return con;
  }

  public static Connection getConnection(String driver, String jdbcurl, String username,
      String password) {
    Connection con = null;
    try {
      Class.forName(driver);
      con = DriverManager.getConnection(jdbcurl, username, password);
      con.setAutoCommit(false);
    } catch (Exception e) {
      throw new RuntimeException("获取连接失败: " + e.getMessage());
    }
    return con;
  }

  public static void closeConnection(Connection con) {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    threadLocal.set(null);
  }

  public static void closeStatement(Statement stmt) {
    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void closeResultSet(ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void rollback(Connection con) {
    try {
      if (con != null) {
        con.rollback();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Long getSequence(Connection con, String seqName) throws SQLException {
    Long seqId = -1L;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    // Oracle
    // final String selectSql = "select " + seqName + ".nextVal from dual";
    // final String selectSql =
    // "SELECT Auto_increment FROM information_schema.tables WHERE table_name='" + seqName + "'";
    logger.info("Get sequence : " + seqName);
    try {
      pstmt = con.prepareStatement(sequenceSql);
      pstmt.setString(1, seqName);
      rs = pstmt.executeQuery(); // .executeQuery(selectSql);

      if (rs.next()) {
        seqId = rs.getLong(1);
      }
    } finally {
      closeResultSet(rs);
      closeStatement(pstmt);
    }
    logger.info("Return Sequence : " + seqId);
    return seqId;
  }

  public static java.sql.Timestamp getCurrentDate(Connection con) {
    // Date cdate = Calendar.getInstance().getTime();
    Timestamp cdate = new Timestamp(Calendar.getInstance().getTimeInMillis());
    Statement stmt = null;
    ResultSet rs = null;
    String selectSql = "select sysdate from dual";
    // Mysql
    // selectSql = "select now()";
    try {
      stmt = con.createStatement();
      rs = stmt.executeQuery(selectSql);

      if (rs.next()) {
        cdate = rs.getTimestamp(1);
      }
    } catch (Exception e) {
      logger.error("getCurrentDate(Connection)", e);

    } finally {
      closeResultSet(rs);
      closeStatement(stmt);
    }

    return cdate;
  }

  public static void setStatementParam(PreparedStatement pstmt, Object[] params) {
    if (params == null || params.length == 0) {
      return;
    }

    try {
      for (int i = 0; i < params.length; i++) {
        int index = i + 1;
        Object param = params[i];

        if (param == null) {
          pstmt.setObject(index, null);
        } else if (param instanceof String) {
          pstmt.setString(index, (String) param);
        } else if (param instanceof Byte) {
          pstmt.setByte(index, (Byte) param);
        } else if (param instanceof Short) {
          pstmt.setShort(index, (Short) param);
        } else if (param instanceof Integer) {
          pstmt.setInt(index, (Integer) param);
        } else if (param instanceof Long) {
          pstmt.setLong(index, (Long) param);
        } else if (param instanceof Float) {
          pstmt.setFloat(index, (Float) param);
        } else if (param instanceof Double) {
          pstmt.setDouble(index, (Double) param);
        } else if (param instanceof BigInteger) {
          pstmt.setBigDecimal(index, new BigDecimal((BigInteger) param));
        } else if (param instanceof BigDecimal) {
          pstmt.setBigDecimal(index, (BigDecimal) param);
        } else if (param instanceof Time) {
          pstmt.setTime(index, (Time) param);
        } else if (param instanceof Timestamp) {
          pstmt.setTimestamp(index, (Timestamp) param);
        } else if (param instanceof java.sql.Date) {
          pstmt.setDate(index, (java.sql.Date) param);
        } else if (param instanceof Date) {
          pstmt.setDate(index, new java.sql.Date(((Date) param).getTime()));
        } else {
          pstmt.setObject(index, param);
        }
      }
    } catch (Exception e) {
      logger.error("setStatementParam(PreparedStatement, Object[])", e);
    }
  }

  public static List<Map<String, Object>> query(Connection con, String sql, Object[] params)
      throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    try {
      ps = con.prepareStatement(sql.toString());
      if (params != null) {
        for (int i = 0; i < params.length; i++) {
          ps.setObject(i + 1, params[i]);
        }
      }

      rs = ps.executeQuery();
      ResultSetMetaData resultSetMetaData = rs.getMetaData();
      int columns = resultSetMetaData.getColumnCount();
      while (rs.next()) {
        Map<String, Object> m = new LinkedHashMap<String, Object>();
        for (int i = 0; i < columns; i++) {
          Object obj = null;
          int type = resultSetMetaData.getColumnType(i + 1);
          int scale = resultSetMetaData.getScale(i + 1);
          String columnName = resultSetMetaData.getColumnName(i + 1);
          switch (type) {
            case Types.OTHER: // -1:
              obj = rs.getObject(columnName);
              break;
            case Types.CLOB: // 1:
              obj = rs.getCharacterStream(columnName);
              break;
            case Types.BLOB:
              obj = rs.getBlob(columnName);
              break;
            case Types.BIGINT:
              obj = Long.valueOf(rs.getLong(columnName));
              break;
            case Types.NUMERIC:
              switch (scale) {
                case 0:
                  obj = Integer.valueOf(rs.getInt(columnName));
                  break;
                case -127:
                  obj = Float.valueOf(rs.getFloat(columnName));
                  break;
                default:
                  obj = Integer.valueOf(rs.getInt(columnName));
              }
              break;
            case Types.VARCHAR:
            case Types.LONGNVARCHAR:
              obj = rs.getString(columnName);
              break;
            case Types.DATE:
              obj = rs.getDate(columnName);
              break;
            case Types.TIMESTAMP:
              obj = rs.getTimestamp(columnName);
              break;
            default:
              obj = rs.getObject(columnName);
          }
          if (rs.wasNull()) {
            obj = null;
          }
          m.put(columnName.toUpperCase(), obj);
        }
        list.add(m);
      }
    } catch (Exception e) {
      logger.error("Query failed.", e);
      throw new Exception(e.getMessage());
    } finally {
      JDBCHelper.closeResultSet(rs);
    }
    return list;
  }

  public static Page<Map<String, Object>> paginate(Connection con, String sql, Object[] params,
      PageContext pageContext) throws Exception {
    int pageNumber = 1;
    int pageSize = 10;
    int size = 0;
    Page<Map<String, Object>> page = new Page<Map<String, Object>>();

    if (pageContext != null) {
      pageNumber = pageContext.getPageNo();
      pageSize = pageContext.getPageSize();
    }
    if (pageNumber < 1) {
      pageNumber = 1;
      pageContext.setPage(1);
    }

    // 统计记录数
    String countSql = "select count(1) as total from (" + sql + ") as t";
    List<Map<String, Object>> result = query(con, countSql, null);
    if (result.size() > 0) {
      Number count = (Number) result.get(0).get("TOTAL");
      size = count.intValue();
    }

    page.setTotalCount(size);
    page.setPageNo(pageNumber);
    page.setPageSize(pageSize);

    // 查询数据记录
    StringBuilder tmpSql = new StringBuilder(256);
    // Oracle 分页查询方式
    // tmpSql.append("SELECT * FROM ");
    // tmpSql.append("  (");
    // tmpSql.append("    SELECT A.*, ROWNUM ROWNUM_");
    // tmpSql.append("      FROM (").append(sql).append(") A");
    // tmpSql.append("      WHERE ROWNUM <= " + pagination.getLast());
    // tmpSql.append("  )");
    // tmpSql.append("WHERE ROWNUM_ >= " + pagination.getFirst());
    // MySQL 分页查询方式
    tmpSql.append("SELECT * FROM (").append(sql)
        .append(") A limit " + pageContext.getOffset() + ", " + pageSize);
    result = query(con, tmpSql.toString(), params);

    page.setResult(result);

    return page;
  }

  public static void executeSql(Connection con, String sql, Object[] params) throws Exception {
    PreparedStatement pstmt = null;
    pstmt = con.prepareStatement(sql);
    JDBCHelper.setStatementParam(pstmt, params);
    pstmt.execute();
    JDBCHelper.closeStatement(pstmt);
  }
}
