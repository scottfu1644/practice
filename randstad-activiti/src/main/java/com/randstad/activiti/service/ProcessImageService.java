package com.randstad.activiti.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.exception.ActivitiImageException;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.randstad.activiti.diagram.CustomProcessDiagramGenerator;
import com.randstad.activiti.util.ActivitiUtils;

/**
 * 
 * activiti流程图上获取各节点的信息.
 * <p>
 * 
 * @author suzu
 */
@Service
public class ProcessImageService {
  private static Logger logger = LoggerFactory.getLogger(ProcessImageService.class);

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
   * 获取流程定义图.
   * 
   * @param processName 流程定义名
   * @return 流程图输入流
   */
  public InputStream getProcessDefinitionImage(String processName) {
    Deployment deployment =
        repositoryService.createDeploymentQuery().processDefinitionKey(processName).singleResult();
    // 从仓库中找需要展示的文件
    String deploymentId = deployment.getId();
    List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
    String imageName = null;
    for (String name : names) {
      if (name.indexOf(".png") >= 0) {
        imageName = name;
      }
    }
    if (imageName != null) {
      // 通过部署ID和文件名称得到文件的输入流
      InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);
      return in;
    }

    return null;
  }

  /**
   * 
   * 获取流程图，并且显示活动节点.
   * 
   * @param executionId Execution对象ID，任务ID或流程实例ID等正在执行的对象ID
   * @return 流程图输入流
   */
  public InputStream generateProcessImage(String executionId) {
    // 查询Execution对象
    Execution execution =
        runtimeService.createExecutionQuery().executionId(executionId).singleResult();

    String processInstanceId = execution.getProcessInstanceId();

    // 查询历史流程实例
    HistoricProcessInstance historicProcessInstance =
        historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
            .singleResult();

    // 查询流程定义对象
    ProcessDefinition processDefinition =
        repositoryService.createProcessDefinitionQuery()
            .processDefinitionId(historicProcessInstance.getProcessDefinitionId()).singleResult();

    // 查询已经执行过的流程节点，包括Gateway等
    List<HistoricActivityInstance> highLightedActivitList =
        historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
            .list();
    // 高亮环节id集合
    List<String> highLightedActivities = new ArrayList<String>();
    // 高亮线路id集合
    ProcessDefinitionEntity definitionEntity =
        (ProcessDefinitionEntity) repositoryService.getProcessDefinition(historicProcessInstance
            .getProcessDefinitionId());
    List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);
    logger.info("Executed flow : {}", highLightedFlows);

    for (HistoricActivityInstance tempActivity : highLightedActivitList) {
      String activityId = tempActivity.getActivityId();
      highLightedActivities.add(activityId);
    }
    logger.info("Executed activity: {}", highLightedActivities);

    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());

    String imageType = "png";

    // 得到正在执行的Activity的Id
    List<String> activityIds = new ArrayList<String>();
    getCurrrentActivity(processInstanceId, activityIds);
    logger.info("Current activity ids : {}", activityIds);

    // BufferedImage processDiagram = generateImage(bpmnModel, imageType,
    // activityIds, highLightedFlows);

    // return convertToSteam(processDiagram, imageType);

    // 中文显示的是口口口，设置字体就好了
    return new CustomProcessDiagramGenerator().generateDiagram(bpmnModel, imageType,
        highLightedActivities, highLightedFlows, activityIds);
    // 单独返回流程图，不高亮显示
    // InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
  }

  /**
   * 
   * 相关使用方法备份用.
   * 
   * @param execution
   * @param processInstanceId
   */
  void backup(Execution execution, String processInstanceId) {
    ProcessInstance processInstance = null;
    if (execution instanceof ProcessInstance) {
      processInstance = (ProcessInstance) execution;
    } else {
      runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
          .singleResult();
    }

    // 查询所有的历史活动记录
    List<HistoricActivityInstance> activityInstances =
        historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
            .list();

    // 查询流程有关的变量
    List<HistoricVariableInstance> variableInstances =
        historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId)
            .list();

    List<HistoricDetail> formProperties =
        historyService.createHistoricDetailQuery().processInstanceId(processInstanceId)
            .formProperties().list();

    // 查询运行时流程实例
    ProcessInstance parentProcessInstance =
        runtimeService.createProcessInstanceQuery()
            .subProcessInstanceId(execution.getProcessInstanceId()).singleResult();
  }

  private void getCurrrentActivity(String processInstanceId, List<String> activityIds) {
    // 查询流程当前活动的执行对象，代办任务节点
    List<Execution> executions =
        runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
    for (Execution exe : executions) {
      List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
      activityIds.addAll(ids);
    }
  }

  /**
   * 
   * 将流程图片转换为InputStream.
   * 
   * @param processDiagram 流程图
   * @param imageType 图片类型，png/jpg...... png is default
   * @return 图片输入流
   */
  private InputStream convertToSteam(BufferedImage processDiagram, String imageType) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      ImageIO.write(processDiagram, imageType, out);

    } catch (IOException e) {
      throw new ActivitiImageException("Error while generating process image", e);
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException ignore) {
        // Exception is silently ignored
      }
    }
    return new ByteArrayInputStream(out.toByteArray());
  }

  /**
   * 获取当前活动的坐标.
   * 
   * @param taskId
   * @return
   */
  public Map<String, Object> getCurrentActivityCoordinates(String taskId) {
    Map<String, Object> coordinates = new HashMap<String, Object>();
    // 1. 获取到当前活动的ID
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    ProcessInstance pi =
        runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId())
            .singleResult();
    String currentActivitiId = pi.getActivityId();
    // 2. 获取到流程定义
    ProcessDefinitionEntity pd =
        (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task
            .getProcessDefinitionId());
    // 3. 使用流程定义通过currentActivitiId得到活动对象
    ActivityImpl activity = pd.findActivity(currentActivitiId);
    // 4. 获取活动的坐标
    coordinates.put("x", activity.getX());
    coordinates.put("y", activity.getY());
    coordinates.put("width", activity.getWidth());
    coordinates.put("height", activity.getHeight());

    // // 如果有多个流程活动节点（并发流程一般有多个活动节点）该方法应该返回一个list，代码应该使用下面的方法
    // // 得到流程执行对象
    // List<Execution> executions =
    // runtimeService.createExecutionQuery().processInstanceId(pi.getId()).list();
    // // 得到正在执行的Activity的Id
    // List<String> activityIds = new ArrayList<String>();
    // for (Execution exe : executions) {
    // List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
    // activityIds.addAll(ids);
    // }
    // List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
    // for (String id : activityIds) {
    // ActivityImpl activity1 = pd.findActivity(id);
    // Map<String, Integer> map = new HashMap<String, Integer>();
    // map.put("x", activity1.getX());
    // map.put("y", activity1.getY());
    // map.put("width", activity1.getWidth());
    // map.put("height", activity1.getHeight());
    // list.add(map);
    // }

    return coordinates;
  }

  /**
   * 获取已经执行过的流程线，用于高亮显示
   * 
   * @param processDefinitionEntity
   * @param historicActivityInstances
   * @return
   */
  private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,
      List<HistoricActivityInstance> historicActivityInstances) {
    // 用以保存高亮的线flowId
    List<String> highFlows = new ArrayList<String>();
    // 对历史流程节点进行遍历
    for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
      // 得到节点定义的详细信息
      ActivityImpl activityImpl =
          processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());
      // 用以保存后需开始时间相同的节点
      List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();
      ActivityImpl sameActivityImpl1 =
          processDefinitionEntity
              .findActivity(historicActivityInstances.get(i + 1).getActivityId());
      // 将后面第一个节点放在时间相同节点的集合里
      sameStartTimeNodes.add(sameActivityImpl1);
      for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
        // 后续第一个节点
        HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);
        // 后续第二个节点
        HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);
        if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
          // 如果第一个节点和第二个节点开始时间相同保存
          ActivityImpl sameActivityImpl2 =
              processDefinitionEntity.findActivity(activityImpl2.getActivityId());
          sameStartTimeNodes.add(sameActivityImpl2);
        } else {
          // 有不相同跳出循环
          break;
        }
      }
      // 取出节点的所有出去的线
      List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
      for (PvmTransition pvmTransition : pvmTransitions) {
        // 对所有的线进行遍历
        ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
        // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
        if (sameStartTimeNodes.contains(pvmActivityImpl)) {
          highFlows.add(pvmTransition.getId());
        }
      }
    }
    return highFlows;
  }
}
