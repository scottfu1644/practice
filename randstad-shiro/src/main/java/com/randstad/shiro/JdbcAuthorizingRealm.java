package com.randstad.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.randstad.system.entity.User;
import com.randstad.system.service.ResourceService;
import com.randstad.system.service.RoleService;
import com.randstad.system.service.UserService;

/**
 * shiro 认证 + 授权 重写
 * 
 * @author walker
 */
@Service
public class JdbcAuthorizingRealm extends AuthorizingRealm {
  private static final Logger logger = LoggerFactory.getLogger(JdbcAuthorizingRealm.class);

  /**
   * 认证回调函数, 登录时调用
   */
  @Resource
  private UserService userService;

  @Resource
  private RoleService roleService;

  @Resource
  private ResourceService resourceService;

  /**
   * 登录验证
   * 
   * @param authcToken 含登录名密码的信息
   * @return 认证信息
   */
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
    logger.info("doGetAuthenticationInfo({})", authcToken);
    if (authcToken == null) {
      throw new AuthenticationException("parameter token is null");
    }
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    // 校验用户名密码
    String username = token.getUsername();
    String password = String.copyValueOf(token.getPassword());
    logger.info("user name:[{}]  password: [{}]", username, password);
    User user = userService.findUserByLoginName(token.getUsername());

    if (user == null) {
      throw new UnknownAccountException(); // 没找到帐号
    }

    if (!password.equals(user.getUserPassword())) {
      throw new IncorrectCredentialsException();
    }
    // 注意此处的返回值没有使用加盐方式,如需要加盐，可以在密码参数上加
    return new SimpleAuthenticationInfo(username, password, getName());
    // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
    // SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
    // user.getUsername(), //用户名
    // user.getPassword(), //密码
    // ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
    // getName() //realm name
  }

  /**
   * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用<br>
   * <p>
   * shiro 权限控制有三种
   * <li>1、通过xml配置资源的权限</li>
   * <li>2、通过shiro标签控制权限</li>
   * <li>3、通过shiro注解控制权限</li>
   */
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    logger.info("doGetAuthorizationInfo({})", principals);
    if (principals == null) {
      throw new AuthorizationException("parameters principals is null");
    }
    // 获取已认证的用户名（登录名）
    String username = (String) super.getAvailablePrincipal(principals);
    if (StringUtils.isBlank(username)) {
      return null;
    }
    Set<String> roleCodes = roleService.findRoleCodeByUsername(username);
    Set<String> functionCodes = resourceService.findFunctionCodeByUser(username);
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    authorizationInfo.setRoles(roleCodes);
    authorizationInfo.setStringPermissions(functionCodes);
    return authorizationInfo;
  }
}
