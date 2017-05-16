package com.randstad.common.ws;

import javax.xml.bind.annotation.XmlRootElement;

import com.randstad.common.page.Page;

/**
 * 
 * Function: API响应数据结构. <br>
 * 
 * @author suzu
 * @param <T>
 */
@XmlRootElement
public class MsgDto<T> {
  public static final String STATUS_SUCCESS = "success";
  public static final String STATUS_FAILURE = "failure";
  public static final String STATUS_NOLOGIN = "nologin";

  // fail
  private String status = STATUS_SUCCESS;
  private String message = "";
  private T data = null;
  // 分页信息，data列表时使用
  private Page<T> page = null;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Page<T> getPage() {
    return page;
  }

  public void setPage(Page<T> page) {
    this.page = page;
  }

  @Override
  public String toString() {
    return "MsgDTO [status=" + getStatus() + ", message=" + getMessage() + ", data=" + getData()
        + "]";
  }

}
