package com.randstad.common.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
// import javax.ws.rs.core.MultivaluedMap;

// import com.randstad.common.page.PageContext;
import com.randstad.common.Constants;

/**
 * 封装pageContext操作
 * 
 * @author liand
 * 
 */
public final class PageContextUtil {

  /**
   * 从UrlInfo.getQueryParameters()获取的MultivaluedMap<String, String>类型转换成PageContext类型
   * 
   * @param urlQueryParameters UrlInfo.getQueryParameters()获取的MultivaluedMap<String, String>
   * @return PageContext类型对象
   */
  // public static PageContext fromUrlQueryParameters(MultivaluedMap<String, String>
  // urlQueryParameters) {
  public static PageContext fromUrlQueryParameters(Map<String, List<String>> urlQueryParameters) {
    PageContext ret = new PageContext();

    if (urlQueryParameters == null) {
      return ret;
    }

    Map<String, Object> params = new HashMap<String, Object>();

    // 循环所有的查询参数
    for (Entry<String, List<String>> kv : urlQueryParameters.entrySet()) {
      List<String> v = kv.getValue();
      if (v == null || v.size() == 0) {
        continue;
      }

      String v2 = v.get(0);
      if (v2 == null || v2.length() == 0) {
        continue;
      }

      String k = kv.getKey();

      // 解析键
      if (Constants.PAGINATION_PARAM_PAGENUM.equals(k)) {
        ret.setPage(Integer.parseInt(v2));
      } else if (Constants.PAGINATION_PARAM_PAGESIZE.equals(k)) {
        ret.setPageSize(Integer.parseInt(v2));
      } else if (Constants.PAGINATION_PARAM_SORT.equals(k)) {
        ret.setSortClause(v2);
      } else {
        params.put(k, v2);
      }
    }

    ret.setParams(params);

    return ret;
  }
}
