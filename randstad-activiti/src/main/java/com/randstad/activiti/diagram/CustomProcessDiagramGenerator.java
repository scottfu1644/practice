package com.randstad.activiti.diagram;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.activiti.bpmn.model.Artifact;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.Lane;
import org.activiti.bpmn.model.Pool;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 生成流程图片.
 * <p>
 * 如果要更改图片生成的逻辑，覆盖默认的内部方法即可
 * 
 * @author suzu
 */
public class CustomProcessDiagramGenerator extends DefaultProcessDiagramGenerator {
  private static final Logger logger = LoggerFactory.getLogger(CustomProcessDiagramGenerator.class);

  public CustomProcessDiagramGenerator() {
    this(1.0);
  }

  public CustomProcessDiagramGenerator(final double scaleFactor) {
    super(scaleFactor);
  }

  /**
   * 
   * 绘制流程图，当前节点红色高亮.
   * 
   * @param bpmnModel
   * @param imageType
   * @param highLightedActivities
   * @param highLightedFlows
   * @param scaleFactor
   * @param currentActivities
   * @return
   */
  public InputStream generateDiagram(BpmnModel bpmnModel, String imageType,
      List<String> highLightedActivities, List<String> highLightedFlows,
      List<String> currentActivities) {
    return generateDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, "宋体",
        "宋体", "宋体", null, 1.0, currentActivities);
  }

  /**
   * 
   * 绘制流程图，当前节点红色高亮.
   * 
   * @param bpmnModel
   * @param imageType
   * @param highLightedActivities
   * @param highLightedFlows
   * @param scaleFactor
   * @param currentActivities
   * @return
   */
  public InputStream generateDiagram(BpmnModel bpmnModel, String imageType,
      List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName,
      String labelFontName, String annotationFontName, List<String> currentActivities) {
    return generateDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows,
        activityFontName, labelFontName, annotationFontName, null, 1.0, currentActivities);
  }

  /**
   * 
   * 绘制流程图，当前节点红色高亮.
   * 
   * @param bpmnModel
   * @param imageType
   * @param highLightedActivities
   * @param highLightedFlows
   * @param scaleFactor
   * @param currentActivities
   * @return
   */
  public InputStream generateDiagram(BpmnModel bpmnModel, String imageType,
      List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName,
      String labelFontName, String annotationFontName, ClassLoader customClassLoader,
      double scaleFactor, List<String> currentActivities) {
    DefaultProcessDiagramCanvas processDiagramCanvas =
        generateProcessDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows,
            activityFontName, labelFontName, annotationFontName, customClassLoader, scaleFactor);
    // 绘制当前节点
    drawCurrentActivity(bpmnModel, currentActivities, processDiagramCanvas);

    return processDiagramCanvas.generateImage(imageType);
  }

  protected static DefaultProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel,
      String imageType, String activityFontName, String labelFontName, String annotationFontName,
      ClassLoader customClassLoader) {
    DefaultProcessDiagramCanvas processDiagramCanvas =
        DefaultProcessDiagramGenerator.initProcessDiagramCanvas(bpmnModel, imageType,
            activityFontName, labelFontName, annotationFontName, customClassLoader);

    try {

      int canvasWidth = (Integer) getFieldValue(processDiagramCanvas, "canvasWidth");
      int canvasHeight = (Integer) getFieldValue(processDiagramCanvas, "canvasHeight");
      int minX = (Integer) getFieldValue(processDiagramCanvas, "minX");
      int minY = (Integer) getFieldValue(processDiagramCanvas, "minY");
      return new CustomProcessDiagramCanvas(canvasWidth, canvasHeight, minX, minY, imageType,
          activityFontName, labelFontName, annotationFontName, customClassLoader);
    } catch (IllegalAccessException | NumberFormatException | NoSuchFieldException
        | SecurityException e) {
      return processDiagramCanvas;
    }
  }

  private static Object getFieldValue(DefaultProcessDiagramCanvas processDiagramCanvas,
      String fieldName) throws NoSuchFieldException, IllegalAccessException {
    Field f = processDiagramCanvas.getClass().getDeclaredField(fieldName);
    f.setAccessible(true);
    return f.get(processDiagramCanvas);
  }

  /**
   * 
   * [生成流程图，返回流程图画布]
   * 
   * @param processDefinition
   * @param highLightedActivities
   * @return
   * @Author: [Double]
   * @CreateDate: [2015-10-22]
   * @Version: [v2.0.0]
   */
  protected DefaultProcessDiagramCanvas generateProcessDiagram(BpmnModel bpmnModel,
      String imageType, List<String> highLightedActivities, List<String> highLightedFlows,
      String activityFontName, String labelFontName, String annotationFontName,
      ClassLoader customClassLoader, double scaleFactor) {


    prepareBpmnModel(bpmnModel);

    DefaultProcessDiagramCanvas processDiagramCanvas =
        initProcessDiagramCanvas(bpmnModel, imageType, activityFontName, labelFontName,
            annotationFontName, customClassLoader);

    // Draw pool shape, if process is participant in collaboration
    for (Pool pool : bpmnModel.getPools()) {
      GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
      processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo);
    }

    // Draw lanes
    for (Process process : bpmnModel.getProcesses()) {
      for (Lane lane : process.getLanes()) {
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
        processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo);
      }
    }

    // Draw activities and their sequence-flows
    for (Process process : bpmnModel.getProcesses()) {
      for (FlowNode flowNode : process.findFlowElementsOfType(FlowNode.class)) {
        drawActivity(processDiagramCanvas, bpmnModel, flowNode, highLightedActivities,
            highLightedFlows, scaleFactor);
      }
    }

    // Draw artifacts
    for (Process process : bpmnModel.getProcesses()) {

      for (Artifact artifact : process.getArtifacts()) {
        drawArtifact(processDiagramCanvas, bpmnModel, artifact);
      }

      List<SubProcess> subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
      if (subProcesses != null) {
        for (SubProcess subProcess : subProcesses) {
          for (Artifact subProcessArtifact : subProcess.getArtifacts()) {
            drawArtifact(processDiagramCanvas, bpmnModel, subProcessArtifact);
          }
        }
      }
    }


    return processDiagramCanvas;
  }

  /**
   * 
   * 绘制当前节点.
   * 
   * @param bpmnModel
   * @param highLightedActivities
   * @param highLightedFlows
   * @param scaleFactor
   * @param processDiagramCanvas
   */
  private void drawCurrentActivity(BpmnModel bpmnModel, List<String> currentActivities,
      DefaultProcessDiagramCanvas processDiagramCanvas) {
    logger.info("drawCurrentActivity: {}", currentActivities);
    if (currentActivities == null || currentActivities.size() == 0) {
      return;
    }
    // Map<String, FlowNode> activities = new HashMap<>();
    for (Process process : bpmnModel.getProcesses()) {
      for (FlowNode flowNode : process.findFlowElementsOfType(FlowNode.class)) {
        if (currentActivities.contains(flowNode.getId())) {
          // 当前节点红色显示
          GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
          ((CustomProcessDiagramCanvas) processDiagramCanvas).drawHighLightRed(
              (int) graphicInfo.getX(), (int) graphicInfo.getY(), (int) graphicInfo.getWidth(),
              (int) graphicInfo.getHeight());
        }
        // activities.put(flowNode.getId(), flowNode);
      }
    }
  }


  /**
   * 
   * [绘制流程图中高亮显示的流程线]
   * 
   * @param processDiagramCanvas
   * @param activity
   * @param highLightedActivities
   * @Author: [Double]
   * @CreateDate: [2015-10-22]
   * @Version: [v2.0.0]
   */
  // protected void drawActivityFlowHighLight(DefaultProcessDiagramCanvas processDiagramCanvas,
  // ActivityImpl activity, List<String> highLightedActivities, int index) {
  // logger.info("第【" + index + "】个节点=" + activity.getId());
  // // Outgoing transitions of activity
  // // 绘制当前节点的所有流出流程线，如果流程线已被执行过，则高亮显示
  // int flowIndex = 1;
  // boolean isHighLightedFlow;
  // ActivityImpl lastActivityImpl = null;
  // for (PvmTransition sequenceFlow : activity.getOutgoingTransitions()) {
  // logger.info("节点的第[" + flowIndex + "]条流出流程线=" + sequenceFlow.getId());
  // isHighLightedFlow = false;
  // // 当前流程线对应的后续节点
  // lastActivityImpl = (ActivityImpl) sequenceFlow.getDestination();
  // logger.info("流程线[" + sequenceFlow.getId() + "]对应的后续节点ID=[" + lastActivityImpl.getId() + "]");
  // // 当前节点的后续节点在需高亮显示的节点List中，并且当前节点已经执行过就是也在高亮显示的节点List中，
  // if (highLightedActivities.contains(lastActivityImpl.getId())
  // && highLightedActivities.contains(activity.getId())) {
  // // 获取在已执行节点List中当前节点的下一个节点ID
  // if (index >= highLightedActivities.size()) {
  // // 没有下一个节点，当前的流程线不高亮显示
  // logger.info("流程线[" + sequenceFlow.getId() + "]不需要高亮显示");
  // } else {
  // // 【注意：以下处理对于并发的处理可能不完善，遇到时再进行具体处理】
  // // 如果下一个节点是当前流程线指向的节点，则流程线高亮显示
  // if (lastActivityImpl.getId().equals(highLightedActivities.get(index))) {
  // isHighLightedFlow = true;
  // logger.info("流程线【" + sequenceFlow.getId() + "】需要高亮显示");
  // }
  // }
  // } else {
  // logger.info("---流程线[" + sequenceFlow.getId() + "]不需要高亮显示");
  // }
  //
  // List<Integer> waypoints = ((TransitionImpl) sequenceFlow).getWaypoints();
  // for (int i = 2; i < waypoints.size(); i += 2) { // waypoints.size() // minimally 4: x1, y1, //
  // // x2, y2
  // boolean drawConditionalIndicator =
  // (i == 2) && sequenceFlow.getProperty(BpmnParse.PROPERTYNAME_CONDITION) != null
  // && !((String) activity.getProperty("type")).toLowerCase().contains("gateway");
  // if (i < waypoints.size() - 2) {
  // // 绘制一条流程线中不带箭头的直线部分
  // if (isHighLightedFlow) {
  // processDiagramCanvas.drawSequenceflowWithoutArrow(waypoints.get(i - 2),
  // waypoints.get(i - 1), waypoints.get(i), waypoints.get(i + 1),
  // drawConditionalIndicator, true, scaleFactor);
  // }
  // } else {
  // // 绘制一条流程线中带箭头的直线部分
  // if (isHighLightedFlow) {
  // processDiagramCanvas.drawSequenceflow(waypoints.get(i - 2), waypoints.get(i - 1),
  // waypoints.get(i), waypoints.get(i + 1), drawConditionalIndicator, scaleFactor);
  // }
  // }
  // }
  // flowIndex++;
  // }
  //
  // // Nested activities (boundary events)
  // // 循环绘制当前节点下的子节点
  // for (ActivityImpl nestedActivity : activity.getActivities()) {
  // drawActivity(processDiagramCanvas, nestedActivity, highLightedActivities);
  // }
  // }
}
