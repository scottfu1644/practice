package com.randstad.activiti.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.randstad.activiti.TaskAction;

/**
 * 
 * 流程相关操作服务.
 * <p>
 * 
 * @author suzu
 */
@Service
public class ProcessService {

  private static Logger logger = LoggerFactory.getLogger(ProcessService.class);

  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private RepositoryService repositoryService;
  @Autowired
  private HistoryService historyService;

  /**
   * 
   * 获取流程实例节点的后续路径或节点信息.
   * 
   * @param taskInstanceId
   * @return 任务节点的后续路径信息
   */
  public List<TaskAction> getTaskActions(String taskInstanceId) {
    List<TaskAction> actions = new ArrayList<>();
    // 根据流程ID获取当前任务
    Task task = taskService.createTaskQuery().taskId(taskInstanceId).singleResult();
    logger.debug("Task id: {}", task.getId());
    logger.debug("Task name: {}", task.getName());
    logger.debug("Task owner: {}", task.getOwner());
    logger.debug("Task category: {}", task.getCategory());
    logger.debug("Task parentTaskId: {}", task.getParentTaskId());
    logger.debug("Task taskDefinitionKey: {}", task.getTaskDefinitionKey());
    logger.debug("Task processDefinitionId: {}", task.getProcessDefinitionId());
    logger.debug("Task executionId: {}", task.getExecutionId());
    logger.debug("Task processInstanceId: {}", task.getProcessInstanceId());
    logger.debug("Task formKey: {}", task.getFormKey());
    logger.debug("Task assignee: {}", task.getAssignee());
    logger.debug("Task tenantId: {}", task.getTenantId());
    logger.debug("Task delegationState: {}", task.getDelegationState());
    logger.debug("Task dueDate: {}", task.getDueDate());
    logger.debug("Task priority: {}", task.getPriority());
    logger.debug("Task processVariables: {}", task.getProcessVariables());
    logger.debug("Task taskLocalVariables: {}", task.getTaskLocalVariables());
    logger.debug("Task createTime: {}", task.getCreateTime());
    logger.debug("Task description: {}", task.getDescription());

    String processDefinitionId = task.getProcessDefinitionId();
    String taskDefinitionKey = task.getTaskDefinitionKey();
    // 根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID：
    // String excId = task.getExecutionId();

    // viewTaskRuntimeInfo(processDefinitionId, excId);

    actions = getTaskActions(processDefinitionId, taskDefinitionKey);

    // viewProcess(def.getProcessDefinition().getId());

    return actions;
  }

  /**
   * 从流程定义获取节点的后续路径或节点信息.
   */
  public List<TaskAction> getTaskActions(String processDefinitionId, String taskDefinitionKey) {
    List<TaskAction> actions = new ArrayList<>();
    // processDefinitionId 对应表ACT_RE_PROCDEF主键信息
    // String processDefinitionId="daling:5:30004";
    // processDefinitionId = "daling:5:30004";
    // taskDefinitionKey = "deptLeaderVerify";
    logger.info("processDefinitionId: {}", processDefinitionId);
    logger.info("taskDefinitionKey: {}", taskDefinitionKey);
    // 获取bpmnModel对象
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
    // 因为我们这里只定义了一个Process 所以获取集合中的第一个即可
    org.activiti.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
    // 获取所有的FlowElement信息
    Collection<FlowElement> flowElements = process.getFlowElements();
    Map<String, FlowElement> flowMap = new HashMap<>();
    for (FlowElement flowElement : flowElements) {
      flowMap.put(flowElement.getId(), flowElement);
    }

    for (FlowElement flowElement : flowElements) {
      // 如果是任务节点
      if (flowElement instanceof UserTask) {
        UserTask userTask = (UserTask) flowElement;
        String taskId = userTask.getId();
        logger.info("Task id: {}", taskId);
        if (taskId.equals(taskDefinitionKey)) {
          // 获取出线信息
          List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows();
          for (SequenceFlow sequenceFlow : outgoingFlows) {
            getFlowActions(sequenceFlow, actions, flowMap);
          }
        }
      }
    }

    return actions;
  }

  /**
   * 
   * 从流程定义获取节点的后续路径或节点信息..
   * 
   * @param sequenceFlow
   * @param actions
   * @param flowMap
   */
  private void getFlowActions(SequenceFlow sequenceFlow, List<TaskAction> actions,
      Map<String, FlowElement> flowMap) {
    String targetRef = sequenceFlow.getTargetRef();
    FlowElement target = flowMap.get(targetRef);
    if (target instanceof ExclusiveGateway) {
      List<SequenceFlow> outgoingFlows = ((Gateway) target).getOutgoingFlows();
      for (SequenceFlow sequenceFlow2 : outgoingFlows) {
        System.out.println(sequenceFlow2.getId());
        System.out.println(sequenceFlow2.getName());
        System.out.println(sequenceFlow2.getAttributes());
        getFlowActions(sequenceFlow2, actions, flowMap);
      }
    } else {
      System.out.println(sequenceFlow.getId());
      System.out.println(sequenceFlow.getName());
      actions.add(new TaskAction(sequenceFlow.getId(), sequenceFlow.getName()));
    }
  }

  /**
   * 
   * 显示流程定义信息的获取方法.
   * 
   * @param processDefinitionId
   */
  public void viewProcess(String processDefinitionId) {
    // processDefinitionId 对应表ACT_RE_PROCDEF主键信息
    // String processDefinitionId="daling:5:30004";
    // processDefinitionId = "daling:5:30004";
    // 获取bpmnModel对象
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
    // 因为我们这里只定义了一个Process 所以获取集合中的第一个即可
    org.activiti.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
    // 获取所有的FlowElement信息
    Collection<FlowElement> flowElements = process.getFlowElements();
    for (FlowElement flowElement : flowElements) {
      // 如果是任务节点
      if (flowElement instanceof UserTask) {
        UserTask userTask = (UserTask) flowElement;
        // 获取入线信息
        List<SequenceFlow> incomingFlows = userTask.getIncomingFlows();
        for (SequenceFlow sequenceFlow : incomingFlows) {
          System.out.println(sequenceFlow.getId());
          System.out.println(sequenceFlow.getName());
          System.out.println(sequenceFlow.getAttributes());
          System.out.println(sequenceFlow.getId() + "-" + sequenceFlow.getConditionExpression()
              + "--" + sequenceFlow.getDocumentation() + "-" + sequenceFlow.getSourceRef() + "--"
              + sequenceFlow.getTargetRef() + "-");
        }
      }
    }
  }

  /**
   * 
   * 展示流程节点运行时信息的获取方法.
   * 
   * @param processDefinitionId
   * @param excId
   * @return
   */
  private ProcessDefinitionEntity viewTaskRuntimeInfo(String processDefinitionId, String excId) {
    // 根据当前任务获取当前流程的流程定义，然后根据流程定义获得所有的节点：
    ProcessDefinitionEntity def =
        (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
    List<ActivityImpl> activitiList = def.getActivities();

    ExecutionEntity execution =
        (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
    String activitiId = execution.getActivityId();
    // 然后循环activitiList 并判断出当前流程所处节点，然后得到当前节点实例，根据节点实例获取所有从当前节点出发的路径，然后根据路径获得下一个节点实例：
    for (ActivityImpl activityImpl : activitiList) {
      String id = activityImpl.getId();
      if (activitiId.equals(id)) {
        // 输出某个节点的某种属性
        logger.info("当前任务：{}", activityImpl.getProperty("name"));
        // 获取从某个节点出来的所有线路
        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
        for (PvmTransition tr : outTransitions) {
          // 获取线路的终点节点
          PvmActivity ac = tr.getDestination();
          String type = (String) ac.getProperty("type");
          logger.info("下一步任务任务：{}", ac);
          logger.info("下一步任务任务 name：{}", ac.getProperty("name"));
          logger.info("下一步任务任务 type：{}", ac.getProperty("type"));
          logger.info("下一步任务任务 activities：{}", ac.getActivities());
          // if(type.equals("exclusiveGateway")) {
          if (type.indexOf("Gateway") >= 0) {
            List<PvmTransition> flows = ac.getOutgoingTransitions();
            System.out.println(flows);
            System.out.println(flows.get(0));
            logger.info("{}", flows);

            for (int i = 0; i < flows.size(); i++) {
              // PvmTransition flow = flows.get(i);
              TransitionImpl flow = (TransitionImpl) flows.get(i);
              logger.info("Flow info: {}", flow.getClass());
              logger.info("Flow info: {}", flow);
              System.out.println(flow.getProperties());
              System.out.println(flow.getId());
              System.out.println(flow.getProperty("name"));
              logger.info("Flow info source: {}", flow.getSource());
              logger.info("Flow info id: {}", flow.getId());
              logger.info("Flow info name: {}", flow.getProperty("name"));
              logger.info("Flow info type: {}", flow.getProperty("type"));
              logger.info("Flow info dest: {}", flow.getDestination());
            }
          }
        }
      }
    }
    return def;
  }

  //////////////////////////////// 流程操作方法 ////////////////////////////////

  /**
   * 
   * 启动流程.
   * 
   * @param processDefinitionKey 流程定义名称
   * @param username 用户登录名
   * @param businessKey 业务ID
   * @param variables 变量
   * @return 流程实例对象
   */
  public ProcessInstance startWorkflow(String processDefinitionKey, String username,
      String businessKey, Map<String, Object> variables) {
    // 设置流程开启人，这个是必要方法
    Authentication.setAuthenticatedUserId(username);
    ProcessInstance processInstance =
        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    String processInstanceId = processInstance.getId();
    logger.debug("start process of {key={}, businessKey={}, processInstanceId={}, variables={}}",
        new Object[] {processDefinitionKey, businessKey, processInstanceId, variables});
    return processInstance;
  }

  /**
   * 
   * 查询代办任务，todoList
   * 
   * @param params 查询任务的参数，包含流程参数和流程、任务变量值
   *        <ul>
   *        流程自有相关参数
   *        <li>processName 流程名称</li>
   *        <li>username 用户名</li>
   *        </ul>
   *        <ul>
   *        分页参数
   *        <li>offset 分页偏移量</li>
   *        <li>size 分页大小</li>
   *        </ul>
   *        <ul>
   *        其他参数
   *        <li>保存在流程和任务的的变量，目前只支持字符串模糊查询</li>
   * @return 任务列表
   */
  public List<Task> findTodoTasks(Map<String, Object> params) {
    Map<String, Object> allParams = new HashMap<String, Object>();
    allParams.putAll(params);
    TaskQuery taskQuery = taskService.createTaskQuery();
    final String processName = (String) allParams.remove("processName");
    final String username = (String) allParams.remove("username");
    final Integer offset = (Integer) allParams.remove("offset");
    final Integer size = (Integer) allParams.remove("size");

    if (StringUtils.isNotBlank(processName)) {
      taskQuery = taskQuery.processDefinitionName(processName);
    }

    if (StringUtils.isNotBlank(username)) {
      // 根据当前人未签收或已签收的任务
      taskQuery = taskQuery.taskCandidateOrAssigned(username);
      // taskQuery = taskQuery.taskOwner(arg0);
    }

    // taskQuery.dueAfter(arg0);
    // taskQuery.dueBefore(arg0);

    for (String key : allParams.keySet()) {
      Object value = allParams.get(key);
      if (value == null) {
        continue;
      }
      // if(value instanceof Collection) {
      // taskQuery.processVariableValueLike(key, value);
      // }
      String strvalue = "%" + value.toString().trim() + "%";
      // 查询流程变量值
      taskQuery.processVariableValueLike(key, strvalue);

      // 查询任务变量值
      taskQuery.taskVariableValueLike(key, strvalue);
    }

    // order
    taskQuery = taskQuery.orderByTaskCreateTime().desc();
    List<Task> tasks = new ArrayList<>();
    if (offset != null && size != null) {
      tasks = taskQuery.listPage(offset, size);
    } else {
      tasks = taskQuery.list();
    }

    return tasks;
  }

  /**
   * 
   * 查询历史任务.
   * 
   * @param processInstanceId
   * @return
   */
  public List<HistoricTaskInstance> findHistoryTaskList(String processInstanceId) {
    List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
        .processInstanceId(processInstanceId).orderByTaskCreateTime().desc().list();
//    if (list != null && list.size() > 0) {
//      for (HistoricTaskInstance hti : list) {
//        System.out.println(hti.getId() + "    " + hti.getName() + "   " + hti.getClaimTime());
//      }
//    }

    return list;
  }

  /**
   * 
   * 查询历史活动.
   * <p> 历史活动包括所有Activity的执行，如Start、Gateway
   * 
   * @param processInstanceId
   * @return
   */
  public List<HistoricActivityInstance> findHistoryActivitiList(String processInstanceId) {
    List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
        .processInstanceId(processInstanceId).list();
    if (list != null && list.size() > 0) {
      for (HistoricActivityInstance hai : list) {
        System.out.println(hai.getId() + "  " + hai.getActivityName());
      }
    }

    return list;
  }

  public void claimTask(String taskId, String username) {
    taskService.claim(taskId, username);
  }

  public void unclaimTask(String taskId) {
    taskService.unclaim(taskId);
  }

  public void reassignTask(String taskId, String assignee) {
    // taskService.c
  }

  /**
   * 
   * 完成任务.
   * <p>
   * 
   * @param taskId 任务ID
   * @param variables 任务变量
   * <li>comment</li>审批意见
   * <li>action</li>选择的执行路径
   */
  public void completeTask(String taskId, Map<String, Object> variables) {
    taskService.complete(taskId, variables);
    // taskService.addComment(arg0, arg1, arg2)
  }
}
