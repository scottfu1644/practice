package com.randstad.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_USER)
public class User implements Serializable {
  private static final long serialVersionUID = -6847569527640601828L;

  public static String tableName = "T_USER";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(USER_NAME)
  private String userName = null;

  // Property of Column(USER_PASSWORD)
  private String userPassword = null;

  // Property of Column(USER_FULL_NAME)
  private String userFullName = null;

  // Property of Column(GENDER)
  private String gender = null;

  // Property of Column(EMAIL)
  private String email = null;

  // Property of Column(MOBILE)
  private String mobile = null;

  // Property of Column(TELEPHONE)
  private String telephone = null;

  // Property of Column(ORG_ID)
  private Long orgId = null;

  // Property of Column(USER_STATUS)
  private Integer userStatus = null;

  // Property of Column(ORDER_NO)
  private Integer orderNo = null;

  // Property of Column(REMARK)
  private String remark = null;

  // Property of Column(CREATE_BY)
  private Long createBy = null;

  // Property of Column(CREATE_DATE)
  private Date createDate = null;

  // Property of Column(UPDATE_BY)
  private Long updateBy = null;

  // Property of Column(UPDATE_DATE)
  private Date updateDate = null;


  public User() {}

  // -----------------Accessors (getters and setters)----------------- //

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return this.userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserFullName() {
    return this.userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTelephone() {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Long getOrgId() {
    return this.orgId;
  }

  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  public Integer getUserStatus() {
    return this.userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  public Integer getOrderNo() {
    return this.orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
    final User other = (User) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "User ( " + "id = " + this.id + tab + "userName = " + this.userName + tab
            + "userPassword = " + this.userPassword + tab + "userFullName = " + this.userFullName
            + tab + "gender = " + this.gender + tab + "email = " + this.email + tab + "mobile = "
            + this.mobile + tab + "telephone = " + this.telephone + tab + "orgId = " + this.orgId
            + tab + "userStatus = " + this.userStatus + tab + "orderNo = " + this.orderNo + tab
            + "remark = " + this.remark + tab + "createBy = " + this.createBy + tab
            + "createDate = " + this.createDate + tab + "updateBy = " + this.updateBy + tab
            + "updateDate = " + this.updateDate + tab + " )";

    return str;
  }

}
