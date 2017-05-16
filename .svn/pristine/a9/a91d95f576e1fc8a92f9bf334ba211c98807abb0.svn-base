package com.randstad.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_ROLE_RESOURCE)
public class RoleResource implements Serializable {
  private static final long serialVersionUID = 1582137995572795413L;

  public static String tableName = "T_ROLE_RESOURCE";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(ROLE_ID)
  private Long roleId = null;

  // Property of Column(RES_ID)
  private Long resId = null;

  // Property of Column(OPERATIONS)
  private String operations = null;


  public RoleResource() {}

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

  public Long getResId() {
    return this.resId;
  }

  public void setResId(Long resId) {
    this.resId = resId;
  }

  public String getOperations() {
    return this.operations;
  }

  public void setOperations(String operations) {
    this.operations = operations;
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
    final RoleResource other = (RoleResource) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "RoleResource ( " + "id = " + this.id + tab + "roleId = " + this.roleId + tab + "resId = "
            + this.resId + tab + "operations = " + this.operations + tab + " )";

    return str;
  }

}
