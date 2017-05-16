package com.randstad.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_ROLE)
public class Role implements Serializable {
  private static final long serialVersionUID = 5872401081153435778L;

  public static String tableName = "T_ROLE";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(ROLE_CODE)
  private String roleCode = null;

  // Property of Column(ROLE_NAME)
  private String roleName = null;

  // Property of Column(ROLE_DESC)
  private String roleDesc = null;

  // Property of Column(ROLE_STATUS)
  private Integer roleStatus = null;

  // Property of Column(WELCOME_URL)
  private String welcomeUrl = null;

  // Property of Column(ORDER_NO)
  private Integer orderNo = null;

  // Property of Column(CREATE_BY)
  private Long createBy = null;

  // Property of Column(CREATE_DATE)
  private Date createDate = null;

  // Property of Column(UPDATE_BY)
  private Long updateBy = null;

  // Property of Column(UPDATE_DATE)
  private Date updateDate = null;


  public Role() {}

  // -----------------Accessors (getters and setters)----------------- //

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleCode() {
    return this.roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDesc() {
    return this.roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  public Integer getRoleStatus() {
    return this.roleStatus;
  }

  public void setRoleStatus(Integer roleStatus) {
    this.roleStatus = roleStatus;
  }

  public String getWelcomeUrl() {
    return this.welcomeUrl;
  }

  public void setWelcomeUrl(String welcomeUrl) {
    this.welcomeUrl = welcomeUrl;
  }

  public Integer getOrderNo() {
    return this.orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }

  public Long getCreateBy() {
    return this.createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Long getUpdateBy() {
    return this.updateBy;
  }

  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateDate() {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
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
    final Role other = (Role) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "Role ( " + "id = " + this.id + tab + "roleCode = " + this.roleCode + tab + "roleName = "
            + this.roleName + tab + "roleDesc = " + this.roleDesc + tab + "roleStatus = "
            + this.roleStatus + tab + "welcomeUrl = " + this.welcomeUrl + tab + "orderNo = "
            + this.orderNo + tab + "createBy = " + this.createBy + tab + "createDate = "
            + this.createDate + tab + "updateBy = " + this.updateBy + tab + "updateDate = "
            + this.updateDate + tab + " )";

    return str;
  }

}
