package com.randstad.system.service;

/**
 * 
 * Function: 系统登入登出api. <br>
 * 
 * @author suzu
 */
public interface LoginService {
  void login(String username, String password);

  void logout();
}
