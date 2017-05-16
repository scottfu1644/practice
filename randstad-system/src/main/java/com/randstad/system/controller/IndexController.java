package com.randstad.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.randstad.system.entity.User;
import com.randstad.system.service.UserService;

/**
 * 
 * Function: 首页跳转控制. <br>
 * 
 * @author suzu
 */
@Controller
// @RequestMapping(value = "/index")
public class IndexController {
  private static Logger logger = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private UserService userService;

  /**
   * 登录页
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    logger.info("Login Page");
    return "login";
  }

  /**
   * 
   * 首页.
   * 
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(ModelMap model, HttpSession session) {
    logger.info("index");
    return "index/index";
  }

  @RequestMapping(value = "admin/login", method = RequestMethod.POST)
  public String login(HttpServletRequest request) {
    logger.info("Login Validation. username: {}, password: {}", request.getParameter("userid"),
        request.getParameter("password"));
    try {
      Subject subject = SecurityUtils.getSubject();
      // 已登陆则 跳到首页
      if (subject.isAuthenticated()) {
        return "redirect:/index";
      }
      // if (result.hasErrors()) {
      // model.addAttribute("error", "参数错误！");
      // return "login";
      // }
      // 身份验证
      // subject.login(new UsernamePasswordToken(user.getUserName(), user.getUserPassword()));
      subject.login(new UsernamePasswordToken(request.getParameter("userid"), request
          .getParameter("password")));
      // 验证成功在Session中保存用户信息
      // final User authUserInfo = userService.findUserById(user.getId());
      final User authUserInfo = userService.findUserByLoginName(request.getParameter("userid"));
      request.getSession().setAttribute("userInfo", authUserInfo);
      logger.info("************************************************");
      logger.info("************  : " + authUserInfo.getId());
      logger.info("************  : " + authUserInfo.getUserName());
      logger.info("************  : " + authUserInfo.getEmail());
      logger.info("************  : " + authUserInfo.getUserFullName());
      logger.info("************  : " + authUserInfo.getCreateDate());
      logger.info("************************************************");
    } catch (AuthenticationException e) {
      // 身份验证失败
      // model.addAttribute("error", "用户名或密码错误 ！");
      return "login";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/index";
  }

  /**
   * 用户登出
   * 
   * @param session
   * @return
   */
  @RequestMapping(value = "admin/logout", method = RequestMethod.GET)
  public String logout(HttpSession session) {
    try {
      // 登出操作
      Subject subject = SecurityUtils.getSubject();
      subject.logout();
      session.removeAttribute("userInfo");
    } catch (Exception e) {
      logger.error("Logout failed", e);
    }
    return "login";
  }

  @RequestMapping(value = "/404", method = RequestMethod.GET)
  public String error404() {
    return "404";
  }

  @RequestMapping(value = "/405", method = RequestMethod.GET)
  public String error405() {
    return "405";
  }

  @RequestMapping(value = "/401", method = RequestMethod.GET)
  public String error401() {
    return "401";
  }

  @RequestMapping(value = "/500", method = RequestMethod.GET)
  public String error500() {
    return "500";
  }
}
