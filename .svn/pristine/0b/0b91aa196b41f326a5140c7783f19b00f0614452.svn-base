import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Array;
// import java.util.Collection;
import java.util.Map;



/**
 * 用于OGNL表达式判断时提供一些方便的工具方法，Struts标签、Mybatis动态SQL都使用了OGNL表达式.
 * <p>
 * <strong>例如：</strong>
 * </p>
 * <p>
 * 
 * <pre>
 * $lt;if test="@Ognl@isNotEmpty(任何java对象)" >
 * $lt;/if>
 * </pre>
 * 
 * </p>
 * 
 * @author Wesley
 * 
 */
public class Ognl {

  /**
   * @see #com.venus.common.lang.StringUtils.isEmpty()
   * @see #java.util.Collection.isEmpty()
   * @see #java.util.Iterable.iterator().hasNext()
   * @see #java.util.Map.isEmpty()
   * @see #java.lang.reflect.Array.getLength(o)
   */
  public static boolean isEmpty(Object o) {
    if (o == null) {
      return true;
    }
    if (o instanceof String) {
      return StringUtils.isEmpty((String) o);
    } else if (o instanceof Iterable<?>) {
      return (((Iterable<?>) o).iterator().hasNext());
    } else if (o instanceof Map<?, ?>) {
      return ((Map<?, ?>) o).isEmpty();
    } else {
      if (o.getClass().isArray()) {
        return Array.getLength(o) <= 0;
      } else {
        return false;
      }
    }
  }

  /**
   * @see #isEmpty(Object).
   */
  public static boolean isNotEmpty(Object o) {

    return !isEmpty(o);
  }

  /**
   * 判断是否是可迭代的对象，如：列表、数组、Map、Collection等对象.
   * 
   * @param o 对象
   * @return boolean
   */
  public static boolean isIterable(Object o) {
    if (o == null) {
      return false;
    }
    if (o instanceof Iterable<?>) {
      return true;
    } else if (o instanceof Map<?, ?>) {
      return true;
    } else {
      if (o.getClass().isArray()) {
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * 判断两个字符串是否相等.
   * 
   * @param str1 字符串1
   * @param str2 字符串2
   * @return boolean
   */
  public static boolean equals(String str1, String str2) {
    return StringUtils.equals(str1, str2);
  }

}
