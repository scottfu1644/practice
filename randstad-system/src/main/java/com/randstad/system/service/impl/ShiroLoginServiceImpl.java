package com.randstad.system.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.randstad.common.exception.ApplicationException;
import com.randstad.system.service.LoginService;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Service("loginService")
public class ShiroLoginServiceImpl implements LoginService {
  private static Logger logger = LoggerFactory.getLogger(ShiroLoginServiceImpl.class);

  @Override
  public void login(String username, String password) {
    String msg = "";
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    token.setRememberMe(true);
    try {
      logger.info("login start.");
      Subject subject = SecurityUtils.getSubject();
      subject.login(token);
      // if (!subject.isAuthenticated()) {
      // throw new
      // msgDto.setStatus(MsgDto.STATUS_FAILURE);
      // msgDto.setMessage("登录失败");
      // }
    } catch (IncorrectCredentialsException e) {
      msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
      throw new ApplicationException(msg, e);
    } catch (ExcessiveAttemptsException e) {
      msg = "登录失败次数过多";
      throw new ApplicationException(msg, e);
    } catch (LockedAccountException e) {
      msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
      throw new ApplicationException(msg, e);
    } catch (DisabledAccountException e) {
      msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
      throw new ApplicationException(msg, e);
    } catch (ExpiredCredentialsException e) {
      msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
      throw new ApplicationException(msg, e);
    } catch (UnknownAccountException e) {
      msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
      throw new ApplicationException(msg, e);
    } catch (UnauthorizedException e) {
      msg = "您没有得到相应的授权！" + e.getMessage();
      throw new ApplicationException(msg, e);
    } finally {
      logger.info("login over.");
    }
  }

  @Override
  public void logout() {
    try {
      logger.info("logout start.");
      Subject subject = SecurityUtils.getSubject();
      subject.logout();
    } catch (Exception e) {
      throw new ApplicationException("Logout error. error info : " + e.getMessage());
    } finally {
      logger.info("logout over.");
    }
  }

}
