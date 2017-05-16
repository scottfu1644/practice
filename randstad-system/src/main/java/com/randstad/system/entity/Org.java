package com.randstad.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_ORG)
public class Org implements Serializable {
  private static final long serialVersionUID = 588022206669517113L;

  public static String tableName = "T_ORG";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(ORG_CODE)
  private String orgCode = null;

  // Property of Column(ORG_NAME)
  private String orgName = null;

  // Property of Column(PARENT_ID)
  private Long parentId = null;

  // Property of Column(ORG_LEVEL)
  private Integer orgLevel = null;

  // Property of Column(ORG_PATH)
  private String orgPath = null;

  // Property of Column(ORG_STATUS)
  private Integer orgStatus = null;

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


  public Org() {}

  // -----------------Accessors (getters and setters)----------------- //

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrgCode() {
    return this.orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getOrgName() {
    return this.orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getOrgLevel() {
    return this.orgLevel;
  }

  public void setOrgLevel(Integer orgLevel) {
    this.orgLevel = orgLevel;
  }

  public String getOrgPath() {
    return this.orgPath;
  }

  public void setOrgPath(String orgPath) {
    this.orgPath = orgPath;
  }

  public Integer getOrgStatus() {
    return this.orgStatus;
  }

  public void setOrgStatus(Integer orgStatus) {
    this.orgStatus = orgStatus;
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
    final Org other = (Org) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "Org ( " + "id = " + this.id + tab + "orgCode = " + this.orgCode + tab + "orgName = "
            + this.orgName + tab + "parentId = " + this.parentId + tab + "orgLevel = "
            + this.orgLevel + tab + "orgPath = " + this.orgPath + tab + "orgStatus = "
            + this.orgStatus + tab + "orderNo = " + this.orderNo + tab + "createBy = "
            + this.createBy + tab + "createDate = " + this.createDate + tab + "updateBy = "
            + this.updateBy + tab + "updateDate = " + this.updateDate + tab + " )";

    return str;
  }

}
