package com.randstad.activiti.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class Util {
  private static final String STR_FORMAT = "0000";

  public static String[] list() {


    String basePath = Util.class.getClassLoader().getResource("/").getPath();

    basePath = basePath.substring(1, basePath.length());
    File file = new File(basePath + File.separator + "diagrams");
    return file.list();
  }

  public static String getUserPin() {
    String userPin = null;
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection principal = (PrincipalCollection) subject.getPrincipals();
    if (principal != null) {
      userPin = String.valueOf(principal.getPrimaryPrincipal());
    }
    return userPin;
  }

  public static String formatID(Integer id) {
    DecimalFormat df = new DecimalFormat(STR_FORMAT);
    return df.format(id);
  }

}
