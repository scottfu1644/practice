package com.randstad.common.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.randstad.common.page.Page;
import com.randstad.common.page.PageContext;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class JdbcBaseDao {
  private static Logger logger = LoggerFactory.getLogger(JdbcBaseDao.class);

  public int batchSize = 20;

  public int fetchSize = 50;

  public void closeStatement(Statement stmt) {
    JDBCHelper.closeStatement(stmt);
  }

  public void closeResultSet(ResultSet rs) {
    JDBCHelper.closeResultSet(rs);
  }

  public void rollback(Connection con) {
    JDBCHelper.rollback(con);
  }

  /**
   * FOR ORACLE.
   * 
   * @param con jdbc connection
   * @param seqName sequence name
   * @return sequence
   * @throws SQLException sql exception
   */
  public Long getSequence(Connection con, String seqName) throws SQLException {
    return JDBCHelper.getSequence(con, seqName);
  }

  public Date getCurrentDate(Connection con) {
    return JDBCHelper.getCurrentDate(con);
  }

  public void setStatementParam(PreparedStatement pstmt, Object[] params) {
    JDBCHelper.setStatementParam(pstmt, params);
  }

  public List<Map<String, Object>> query(Connection con, String sql, Object[] params)
      throws Exception {
    return JDBCHelper.query(con, sql, params);
  }

  public Page<Map<String, Object>> paginate(Connection con, String sql, Object[] params,
      PageContext pageContext) throws Exception {
    return JDBCHelper.paginate(con, sql, params, pageContext);
  }

  public static void executeSql(Connection con, String sql, Object[] params) throws Exception {
    JDBCHelper.executeSql(con, sql, params);
  }

}
