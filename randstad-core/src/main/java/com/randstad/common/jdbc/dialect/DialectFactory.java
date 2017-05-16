package com.randstad.common.jdbc.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class DialectFactory {

  /**
   * Slf4j Logger
   **/
  private static Logger logger = LoggerFactory.getLogger(DialectFactory.class);

  public DialectFactory() {}

  public Dialect getDialect(String dialectName) {
    Dialect dialect = new MySqlDialect();
    Dialect.Type dialectType = null;
    try {
      dialectType = Dialect.Type.valueOf(dialectName.toLowerCase());
    } catch (Exception e) {
      logger.warn("Property dialect is invalid, use default 'mysql' ");
      dialectType = Dialect.Type.mysql;
    }

    switch (dialectType) {
      case mysql:
        dialect = new MySqlDialect();
        break;
      case db2:
        dialect = new DB2Dialect();
        break;
      case h2:
        dialect = new H2Dialect();
        break;
      case hsql:
        dialect = new HSqlDialect();
        break;
      case informix:
        dialect = new InformixDialect();
        break;
      case postgresql:
        dialect = new PostgreSQLDialect();
        break;
      case sqlserver:
        dialect = new SqlServerDialect();
        break;
      case sqlserver2012:
        dialect = new SqlServer2012Dialect();
        break;
      case oracle:
        dialect = new OracleDialect();
        break;
      default:
        dialect = new MySqlDialect();
    }

    logger.debug("Database dialect is " + dialect);

    return dialect;
  }

  /**
   * 
   * @param args
   * 
   */
  public static void main(String[] args) {

  }

}
