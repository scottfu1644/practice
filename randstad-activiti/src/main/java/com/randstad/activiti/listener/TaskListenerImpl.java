package com.randstad.activiti.listener;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class TaskListenerImpl implements TaskListener {

  private static final long serialVersionUID = -952918962785362518L;

  @Resource
  IdentityService identityService;

  @Override
  public void notify(DelegateTask delegateTask) {
    List<User> users = identityService.createUserQuery().memberOfGroup("1483927443676").list();
    for (User user : users) {
      // 分配组任务的办理人
      delegateTask.addCandidateUser(user.getId());
    }
  }


}
