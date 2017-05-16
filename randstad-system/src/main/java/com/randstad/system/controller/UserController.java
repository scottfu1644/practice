/**
 * Copy right.
 * 
 * Project Name: core <br>
 * Date: 2017年4月7日 上午10:31:33 <br/>
 * Function: 用户管理API. <br>
 * History : 1. [2017-04-07] Create by suzu
 */
package com.randstad.system.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.ws.rs.PathParam;

import com.randstad.common.page.PageContext;

// import javax.ws.rs.Consumes;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.Produces;

import com.randstad.common.ws.MsgDto;
import com.randstad.system.entity.User;
import com.randstad.system.service.UserService;

/**
 * Function: 用户管理API. <br>
 * 
 * @author suzu
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequiresPermissions(value={"user:view", "user"}, logical=Logical.OR)
//  @RequiresPermissions({"user:view", "user"})
  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  public MsgDto<List<User>> getAllUser() {
    MsgDto<List<User>> msg = new MsgDto<>();
    try {
      List<User> users = userService.findUserByParams(null);
      msg.setData(users);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("=========getAllUser=========", e);
      msg.setMessage(e.getMessage());
      msg.setStatus(MsgDto.STATUS_FAILURE);
    }
    return msg;
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  @ResponseBody
  public MsgDto<User> getUser(@PathParam("userId") Long userId) {
    MsgDto<User> msg = new MsgDto<>();
    try {
      User user = userService.findUserById(userId);
      msg.setData(user);
    } catch (Exception e) {
      logger.error("=========getUserById=========", e);
      msg.setMessage(e.getMessage());
      msg.setStatus(MsgDto.STATUS_FAILURE);
    }
    return msg;
  }

  // -------------------Retrieve All Users--------------------------------------------------------

  @RequestMapping(value = "/listUser", method = RequestMethod.GET)
  @ResponseBody
  public List<User> listAllUsers() {
    System.out.println("listUser>>>>>>>>>>>>>>>>>>");
    List<User> users = userService.findAllActiveUser();
    return users;
  }

  @RequestMapping(value = "/getUserPage")
  @ResponseBody
  public MsgDto<List<User>> getUserPage(int page, int size) {
    MsgDto<List<User>> msgDto = new MsgDto<>();
    try {
      PageContext pageContext = new PageContext();
      pageContext.setPage(page);
      pageContext.setPageSize(size);
      List<User> users = userService.findUserPage(pageContext).getResult();
      msgDto.setData(users);
    } catch (Exception e) {
      e.printStackTrace();
      msgDto.setMessage(e.getMessage());
      msgDto.setStatus(MsgDto.STATUS_FAILURE);
    }
    return msgDto;
  }

  @RequestMapping(value = "/getUser", method = RequestMethod.POST)
  @ResponseBody
  public MsgDto<User> getUser(@RequestBody User user) {
    MsgDto<User> msgDto = new MsgDto<>();
    System.out.println("Userid>>>>>>>>>>>>>>>>>>" + user.getId());
    User userDetail = userService.findUserById(user.getId());
    msgDto.setData(userDetail);
    System.out.println("UserName>>>>>>>>>>>>>>>>>>" + user.getUserFullName());
    return msgDto;
  }

  @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
  @ResponseBody
  public void updateUser(@RequestBody User user) {
    System.out.println("update Userid>>>>>>>>>>>>>>>>>>" + user.getId());
    userService.updateUser(user);
  }

  @RequestMapping("/getPageCount")
  @ResponseBody
  public MsgDto<Integer> getPageCount() {
    MsgDto<Integer> msgDto = new MsgDto<>();
    Integer count = 0;
    try {
      count = userService.findAllUser().size();
      msgDto.setData(count);
    } catch (Exception e) {
      logger.error("getPageCount()", e);
      msgDto.setStatus(MsgDto.STATUS_FAILURE);
      msgDto.setMessage(e.getMessage());
    }
    return msgDto;
  }

}
