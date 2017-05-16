package com.randstad.system.entity;

import java.io.Serializable;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_ROLE_USER)
public class RoleUser implements Serializable {
  private static final long serialVersionUID = -7558290473959183289L;

  public static String tableName = "T_ROLE_USER";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(ROLE_ID)
  private Long roleId = null;

  // Property of Column(USER_ID)
  private Long userId = null;


  public RoleUser() {}

  // -----------------Accessors (getters and setters)----------------- //

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final RoleUser other = (RoleUser) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "RoleUser ( " + "id = " + this.id + tab + "roleId = " + this.roleId + tab + "userId = "
            + this.userId + tab + " )";

    return str;
  }

}
