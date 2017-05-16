/**
 * Copy right.
 * 
 * Project Name: randstad-system <br>
 * Date: 2017年4月9日 下午6:59:28 <br/>
 * Function: TODO(suzu) ADD FUNCTION. <br>
 * History : 1. [2017-04-09] Create by suzu
 */
package com.randstad.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.randstad.common.ws.MsgDto;
import com.randstad.system.service.LoginService;

/**
 * Function: 登录API. <br>
 * 
 * @author suzu
 */
@RestController
// @RequestMapping("/user")
public class LoginController {
  private static Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private LoginService loginService;

  /**
   * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
   * 
   * @param params 登录参数
   * @return 登录执行结果信息
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public MsgDto<Object> doLogin(@RequestBody Map<String, Object> params) {
    logger.info("doLogin {}", params);
    String username = (String) params.get("username"); // request.getParameter("userName");
    String password = (String) params.get("password"); // request.getParameter("password");
    System.out.println(username);
    System.out.println(password);
    MsgDto<Object> msgDto = new MsgDto<>();

    try {
      loginService.login(username, password);
      msgDto.setStatus(MsgDto.STATUS_SUCCESS);
      msgDto.setMessage("登录成功");
    } catch (Exception e) {
      msgDto.setStatus(MsgDto.STATUS_FAILURE);
      msgDto.setMessage(e.getMessage());
    }

    logger.info("doLogin : " + msgDto);

    return msgDto;
  }

  /**
   * 登出系统
   * 
   * @return 登出执行结果信息
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  @ResponseBody
  public MsgDto<Object> doLogout() {
    logger.info("doLogout");
    MsgDto<Object> msgDto = new MsgDto<>();
    try {
      loginService.logout();
    } catch (Exception e) {
      msgDto.setStatus(MsgDto.STATUS_FAILURE);
      msgDto.setMessage(e.getMessage());
    }
    logger.info("doLogout : " + msgDto);

    return msgDto;
  }
}
