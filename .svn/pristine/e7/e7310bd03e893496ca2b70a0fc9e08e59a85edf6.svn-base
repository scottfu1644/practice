package com.randstad.activiti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Controller
@RequestMapping("/user2")
public class UserController22 {

  @Resource
  RepositoryService repositoryService;

  @Resource
  RuntimeService runtimeService;

  @Resource
  FormService formService;

  @Resource
  IdentityService identityService;

  @Resource
  TaskService taskService;

  @Resource
  HistoryService historyService;

  @Resource
  ManagementService managementService;

  @RequestMapping("info")
  public ModelAndView userPage(String userId, ModelAndView mav) {

    List<ProcessInstance> processInstances =
        runtimeService.createProcessInstanceQuery().includeProcessVariables().list();

    Set<String> list = new HashSet<String>();

    if (processInstances.size() > 0) {
      for (int i = 0; i < processInstances.size(); i++) {
        Map<String, Object> vars = processInstances.get(i).getProcessVariables();
        for (String variableName : vars.keySet()) {
          String val = (String) vars.get(variableName);
          if (variableName.equals("applyUserId")) {
            if (userId.equals(val)) {
              String processInstanceId = processInstances.get(i).getProcessInstanceId();
              list.add(processInstanceId);
            }
          }
        }
      }
    }


    List<Task> myTasks = taskService.createTaskQuery().taskCandidateUser(userId).list();

    List<ProcessInstance> myProcessInstances = new ArrayList<ProcessInstance>();
    if (list.size() > 0) {
      myProcessInstances =
          runtimeService.createProcessInstanceQuery().processInstanceIds(list).list();
    }


    mav.setViewName("user/info");
    User user = identityService.createUserQuery().userId(userId).singleResult();
    List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().list();
    mav.addObject("myProcessInstances", myProcessInstances);
    mav.addObject("processList", processList);
    mav.addObject("tasks", myTasks);
    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping("applyPage")
  public ModelAndView applyPage(String processDefinitionId, String userId, ModelAndView mav) {
    mav.setViewName("user/applypage");
    List<User> users = identityService.createUserQuery().list();
    mav.addObject("users", users);
    mav.addObject("processDefinitionId", processDefinitionId);
    mav.addObject("userId", userId);
    return mav;
  }

  @RequestMapping("apply")
  public ModelAndView apply(String processDefinitionId, String userId, String candidateId,
      ModelAndView mav) {

    Map<String, Object> variables = new HashMap<String, Object>();
    // variables.put("userId", candidateId);

    variables.put("userID", "1483792076260,1483792088530");
    variables.put("applyUserId", userId);
    // ProcessInstance processInstance =
    // runtimeService.startProcessInstanceById(processDefinitionId, variables);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test01", variables);

    // ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test01");


    String processInstanceId = processInstance.getId();
    System.out.println(processInstanceId);

    mav.setViewName("user/info");
    return mav;
  }

  @RequestMapping("managent")
  public ModelAndView managentPage(String userId, String taskId, ModelAndView mav) {
    mav.setViewName("user/managentPage");
    Task userTask = taskService.createTaskQuery().taskId(taskId).singleResult();
    List<User> users = identityService.createUserQuery().list();
    mav.addObject("users", users);
    mav.addObject("userTask", userTask);
    mav.addObject("userId", userId);
    return mav;
  }

  @RequestMapping("complete")
  public ModelAndView complete(String userId, String taskId, ModelAndView mav) {
    // taskService.complete(taskId);

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("userIDb", "1483688906020");
    taskService.complete(taskId, variables);

    /*
     * List<ProcessInstance> processInstances =
     * runtimeService.createProcessInstanceQuery().includeProcessVariables().list(); Set<String>
     * list = new HashSet<String>(); if(processInstances.size()>0){ for (int i = 0; i <
     * processInstances.size(); i++) { Map<String, Object> vars =
     * processInstances.get(i).getProcessVariables(); for (String variableName : vars.keySet()) {
     * String val = (String) vars.get(variableName); if(variableName.equals("applyUserId")){
     * if(userId.equals(val)){ String processInstanceId =
     * processInstances.get(i).getProcessInstanceId(); list.add(processInstanceId); } } } } }
     * 
     * List<ProcessInstance> myProcessInstances = new ArrayList<ProcessInstance>(); if(list.size() >
     * 0){ myProcessInstances =
     * runtimeService.createProcessInstanceQuery().processInstanceIds(list).list(); }
     * 
     * List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
     */

    mav.setViewName("user/info");
    /*
     * User user = identityService.createUserQuery().userId(userId).singleResult();
     * List<ProcessDefinition> processList =
     * repositoryService.createProcessDefinitionQuery().list(); mav.addObject("myProcessInstances",
     * myProcessInstances); mav.addObject("processList", processList); mav.addObject("tasks",
     * tasks); mav.addObject("user", user);
     */
    return mav;
  }
}
