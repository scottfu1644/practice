package com.randstad.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Controller
@RequestMapping("/test")
public class TestController {
  @RequestMapping(value = "/test1", method = RequestMethod.GET)
  public String listAllUsers() {
    System.out.println("test>>>>>>>>>>>>>>>>>>");
    return "test/test1";
  }

  @RequestMapping(value = "/computers", method = RequestMethod.GET)
  public String listAllUser() {
    System.out.println("test22222222222");
    return "test/computers";
  }

  @RequestMapping(value = "/getUser", method = RequestMethod.GET)
  public String getUser() {
    System.out.println("angular111111111111111");
    return "user/userIndex2";

  }

}
