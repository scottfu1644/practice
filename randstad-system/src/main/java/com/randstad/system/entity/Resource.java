package com.randstad.system.entity;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.randstad.common.tree.TreeNode;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
// Entity of Table(T_RESOURCE)
public class Resource implements Serializable, TreeNode<Resource> {
  private static final long serialVersionUID = 587083525894580955L;

  public static String tableName = "T_RESOURCE";

  // -----------------Properties (Instance Variables)----------------- //

  // Property of Primary key Column(ID)
  private Long id = null;

  // Property of Column(RES_CODE)
  private String resCode = null;

  // Property of Column(RES_TYPE)
  private String resType = null;

  // Property of Column(RES_NAME)
  private String resName = null;

  // Property of Column(RES_DESC)
  private String resDesc = null;

  // Property of Column(PARENT_ID)
  private Long parentId = null;

  // Property of Column(RES_STATUS)
  private Integer resStatus = null;

  // Property of Column(RES_MODULE)
  private String resModule = null;

  // Property of Column(RES_URL)
  private String resUrl = null;

  // Property of Column(RES_ICON)
  private String resIcon = null;

  // Property of Column(ORDER_NO)
  private Integer orderNo = null;

  // Property of Column(OPERATIONS)
  private String operations = null;

  // Additional field
  private List<Operation> operationList = new ArrayList<Operation>();

  private List<Operation> asssignedOpts = new ArrayList<Operation>();

  private Resource parent;

  private List<Resource> children = new ArrayList<Resource>();

  public Resource() {}

  // -----------------Accessors (getters and setters)----------------- //

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getResCode() {
    return this.resCode;
  }

  public void setResCode(String resCode) {
    this.resCode = resCode;
  }

  public String getResType() {
    return this.resType;
  }

  public void setResType(String resType) {
    this.resType = resType;
  }

  public String getResName() {
    return this.resName;
  }

  public void setResName(String resName) {
    this.resName = resName;
  }

  public String getResDesc() {
    return this.resDesc;
  }

  public void setResDesc(String resDesc) {
    this.resDesc = resDesc;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getResStatus() {
    return this.resStatus;
  }

  public void setResStatus(Integer resStatus) {
    this.resStatus = resStatus;
  }

  public String getResModule() {
    return this.resModule;
  }

  public void setResModule(String resModule) {
    this.resModule = resModule;
  }

  public String getResUrl() {
    return this.resUrl;
  }

  public void setResUrl(String resUrl) {
    this.resUrl = resUrl;
  }

  public String getResIcon() {
    return this.resIcon;
  }

  public void setResIcon(String resIcon) {
    this.resIcon = resIcon;
  }

  public Integer getOrderNo() {
    return this.orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }

  public String getOperations() {
    return this.operations;
  }

  public void setOperations(String operations) {
    operationList = new ArrayList<Operation>();
    if (operations == null) {
      this.operations = null;
    } else {
      this.operations = operations.trim();
    }

    if (StringUtils.isNotEmpty(this.operations)) {
      String[] opts = operations.split(";");
      for (String opt : opts) {
        String o = opt == null ? null : opt.trim();
        if (StringUtils.isEmpty(opt)) {
          continue;
        }

        String[] data = o.split(",");
        Operation operation = new Operation();
        operation.setCode(data[0]);
        operation.setName(data[1]);

        if (!operationList.contains(operation)) {
          operationList.add(operation);
        }
      }
    }
  }

  public List<Operation> getOperationList() {
    return operationList;
  }

  public List<Operation> getAsssignedOpts() {
    return asssignedOpts;
  }

  public void setAsssignedOpts(String asssignedOperations) {
    asssignedOpts = new ArrayList<Operation>();
    if (StringUtils.isEmpty(asssignedOperations)) {
      return;
    } else {
      String[] opts = asssignedOperations.split(",");
      for (String opt : opts) {
        if (opt == null || opt.trim().length() == 0) {
          continue;
        }

        Operation op = new Operation();
        op.setCode(opt.trim());

        if (!asssignedOpts.contains(op)) {
          asssignedOpts.add(op);
        }
      }
    }
  }

  // -------- implement TreeNode --------
  @SuppressWarnings("unchecked")
  public Long id() {
    return this.getId();
  }

  @SuppressWarnings("unchecked")
  public Long parentId() {
    return getParentId();
  }

  public void parent(Resource parent) {
    this.setParent(parent);
  }

  public List<Resource> children() {
    return children;
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
    final Resource other = (Resource) obj;
    return (this.id == null ? other.id == null : this.id.equals(other.id));
  }

  public String toString() {
    final String tab = "  ";
    String str = "";
    str =
        "Resource ( " + "id = " + this.id + tab + "resCode = " + this.resCode + tab + "resType = "
            + this.resType + tab + "resName = " + this.resName + tab + "resDesc = " + this.resDesc
            + tab + "parentId = " + this.parentId + tab + "resStatus = " + this.resStatus + tab
            + "resModule = " + this.resModule + tab + "resUrl = " + this.resUrl + tab
            + "resIcon = " + this.resIcon + tab + "orderNo = " + this.orderNo + tab
            + "operations = " + this.operations + tab + " )";

    return str;
  }

  /**
   * @return the parent
   */
  public Resource getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(Resource parent) {
    this.parent = parent;
  }

}
