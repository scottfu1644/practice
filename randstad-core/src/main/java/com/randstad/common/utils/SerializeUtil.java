package com.randstad.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * Java原生版的 Serialize
 * 
 * <p>
 * 
 * @author walker 1.0,2016年6月2日 <br>
 * 
 */
@SuppressWarnings("unchecked")
public class SerializeUtil {
  public static byte[] serialize(Object value) {
    if (value == null) {
      throw new NullPointerException("Can't serialize null");
    }
    byte[] rv = null;
    ByteArrayOutputStream bos = null;
    ObjectOutputStream os = null;
    try {
      bos = new ByteArrayOutputStream();
      os = new ObjectOutputStream(bos);
      os.writeObject(value);
      os.close();
      bos.close();
      rv = bos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
      // LoggerUtils.fmtError(CLAZZ,e, "serialize error %s", JSONObject.valueToString(value));
    } finally {
      close(os);
      close(bos);
    }
    return rv;
  }


  public static Object deserialize(byte[] in) {
    return deserialize(in, Object.class);
  }

  public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
    Object rv = null;
    ByteArrayInputStream bis = null;
    ObjectInputStream is = null;
    try {
      if (in != null) {
        bis = new ByteArrayInputStream(in);
        is = new ObjectInputStream(bis);
        rv = is.readObject();
      }
    } catch (Exception e) {
      e.printStackTrace();
      // LoggerUtils.fmtError(CLAZZ,e, "serialize error %s", in);
    } finally {
      close(is);
      close(bis);
    }
    return (T) rv;
  }

  private static void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        // LoggerUtils.fmtError(CLAZZ, "close stream error");
        e.printStackTrace();
      }
    }
  }

}
