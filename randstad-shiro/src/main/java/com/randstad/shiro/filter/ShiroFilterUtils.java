package com.randstad.shiro.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

// import org.json.JSONObject;
// import com.randstad.common.utils.LoggerUtils;

/**
 * 
 * Shiro Filter 工具类
 * 
 * <p>
 * *******
 * <p>
 * 
 * @author walker
 * 
 */
public class ShiroFilterUtils {
  private static Logger logger = LoggerFactory.getLogger(ShiroFilterUtils.class);
  // 登录页面
  static final String LOGIN_URL = "/u/login.shtml";
  // 踢出登录提示
  static final String KICKED_OUT = "/open/kickedOut.shtml";
  // 没有权限提醒
  static final String UNAUTHORIZED = "/open/unauthorized.shtml";

  /**
   * 是否是Ajax请求
   * 
   * @param request
   * @return
   */
  public static boolean isAjax(ServletRequest request) {
    return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
        .getHeader("X-Requested-With"));
  }


  /**
   * response 输出JSON
   * 
   * @param response
   * @param resultMap
   * @throws IOException
   */
  public static void out(ServletResponse response, Object value) throws IOException {
    try {
      // response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json; charset=UTF-8");
      PrintWriter out = response.getWriter();
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(out, value);
      out.flush();
      out.close();
    } catch (Exception e) {
      logger.error("Output JSON error.", e);
    }
  }
}
