package com.randstad.common.jdbc;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class JDBCTypeResolver {
  // jdbc type name
  private Map<Integer, String> jdbcTypeMap;

  // java type name
  private Map<Integer, String> javaTypeMap;

  public JDBCTypeResolver() {
    jdbcTypeMap = new HashMap<Integer, String>();
    jdbcTypeMap.put(Types.ARRAY, "ARRAY");
    jdbcTypeMap.put(Types.BIGINT, "BIGINT");
    jdbcTypeMap.put(Types.BINARY, "BINARY");
    jdbcTypeMap.put(Types.BIT, "BIT");
    jdbcTypeMap.put(Types.BLOB, "BLOB");
    jdbcTypeMap.put(Types.BOOLEAN, "BOOLEAN");
    jdbcTypeMap.put(Types.CHAR, "CHAR");
    jdbcTypeMap.put(Types.CLOB, "CLOB");
    jdbcTypeMap.put(Types.DATALINK, "DATALINK");
    jdbcTypeMap.put(Types.DATE, "DATE");
    jdbcTypeMap.put(Types.DISTINCT, "DISTINCT");
    jdbcTypeMap.put(Types.DOUBLE, "DOUBLE");
    jdbcTypeMap.put(Types.DECIMAL, "DECIMAL");
    jdbcTypeMap.put(Types.FLOAT, "FLOAT");
    jdbcTypeMap.put(Types.INTEGER, "INTEGER");
    jdbcTypeMap.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
    jdbcTypeMap.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
    jdbcTypeMap.put(Types.LONGVARBINARY, "LONGVARBINARY");
    jdbcTypeMap.put(Types.LONGVARCHAR, "LONGVARCHAR");
    jdbcTypeMap.put(Types.NCHAR, "NCHAR");
    jdbcTypeMap.put(Types.NCLOB, "NCLOB");
    jdbcTypeMap.put(Types.NVARCHAR, "NVARCHAR");
    jdbcTypeMap.put(Types.NULL, "NULL");
    jdbcTypeMap.put(Types.NUMERIC, "NUMERIC");
    jdbcTypeMap.put(Types.OTHER, "OTHER");
    jdbcTypeMap.put(Types.REAL, "REAL");
    jdbcTypeMap.put(Types.REF, "REF");
    jdbcTypeMap.put(Types.SMALLINT, "SMALLINT");
    jdbcTypeMap.put(Types.STRUCT, "STRUCT");
    jdbcTypeMap.put(Types.TIME, "TIME");
    jdbcTypeMap.put(Types.TIMESTAMP, "TIMESTAMP");
    jdbcTypeMap.put(Types.TINYINT, "TINYINT");
    jdbcTypeMap.put(Types.VARBINARY, "VARBINARY");
    jdbcTypeMap.put(Types.VARCHAR, "VARCHAR");

    javaTypeMap = new HashMap<Integer, String>();
    javaTypeMap.put(Types.ARRAY, Object.class.getName());
    javaTypeMap.put(Types.BIGINT, Long.class.getName());
    javaTypeMap.put(Types.BINARY, "byte[]");
    javaTypeMap.put(Types.BIT, Boolean.class.getName());
    javaTypeMap.put(Types.BLOB, "byte[]");
    javaTypeMap.put(Types.BOOLEAN, Boolean.class.getName());
    javaTypeMap.put(Types.CHAR, String.class.getName());
    javaTypeMap.put(Types.CLOB, String.class.getName());
    javaTypeMap.put(Types.DATALINK, Object.class.getName());
    javaTypeMap.put(Types.DATE, Date.class.getName());
    javaTypeMap.put(Types.DISTINCT, Object.class.getName());
    javaTypeMap.put(Types.DOUBLE, Double.class.getName());
    javaTypeMap.put(Types.FLOAT, Double.class.getName());
    javaTypeMap.put(Types.INTEGER, Integer.class.getName());
    javaTypeMap.put(Types.JAVA_OBJECT, Object.class.getName());
    javaTypeMap.put(Types.LONGNVARCHAR, String.class.getName());
    javaTypeMap.put(Types.LONGVARBINARY, "byte[]");
    javaTypeMap.put(Types.LONGVARCHAR, String.class.getName());
    javaTypeMap.put(Types.NCHAR, String.class.getName());
    javaTypeMap.put(Types.NCLOB, String.class.getName());
    javaTypeMap.put(Types.NVARCHAR, String.class.getName());
    javaTypeMap.put(Types.NULL, Object.class.getName());
    javaTypeMap.put(Types.OTHER, Object.class.getName());
    javaTypeMap.put(Types.REAL, Float.class.getName());
    javaTypeMap.put(Types.REF, Object.class.getName());
    javaTypeMap.put(Types.SMALLINT, Short.class.getName());
    javaTypeMap.put(Types.STRUCT, Object.class.getName());
    javaTypeMap.put(Types.TIME, Date.class.getName());
    javaTypeMap.put(Types.TIMESTAMP, Date.class.getName());
    javaTypeMap.put(Types.TINYINT, Byte.class.getName());
    javaTypeMap.put(Types.VARBINARY, "byte[]");
    javaTypeMap.put(Types.VARCHAR, String.class.getName());
  }

  public String getJavaType(Integer jdbcType) {
    String answer = javaTypeMap.get(jdbcType);
    return answer;
  }

  public String getJavaType(Integer jdbcType, int length, int scale) {
    String answer = javaTypeMap.get(jdbcType);

    if (answer == null) {
      switch (jdbcType) {
        case Types.DECIMAL:
        case Types.NUMERIC:
          if (scale > 0 || length > 18) {
            answer = BigDecimal.class.getName();
          } else if (length > 9) {
            answer = Long.class.getName();
          } else if (length > 4) {
            answer = Integer.class.getName();
          } else {
            answer = Short.class.getName();
          }
          break;

        default:
          answer = null;
          break;
      }
    } else {
      answer = Object.class.getName();
    }

    return answer;
  }

  public String getJdbcTypeName(Integer jdbcType) {
    String answer = jdbcTypeMap.get(jdbcType);

    return answer;
  }
}
