package com.randstad.activiti.interceptor;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.image.ProcessDiagramGenerator;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
public class ProcessInstanceDiagramCmd implements Command<InputStream> {

  protected String processInstanceId;

  public ProcessInstanceDiagramCmd(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public InputStream execute(CommandContext commandContext) {
    ExecutionEntityManager executionEntityManager = commandContext.getExecutionEntityManager();
    ExecutionEntity executionEntity = executionEntityManager.findExecutionById(processInstanceId);
    List<String> activiityIds = executionEntity.findActiveActivityIds();
    String processDefinitionId = executionEntity.getProcessDefinitionId();

    GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(processDefinitionId);
    BpmnModel bpmnModel = getBpmnModelCmd.execute(commandContext);

    ProcessEngineImpl defaultProcessEngine =
        (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    ProcessEngineConfiguration processEngineConfiguration =
        defaultProcessEngine.getProcessEngineConfiguration();

    ProcessDiagramGenerator processDiagramGenerator =
        processEngineConfiguration.getProcessDiagramGenerator();

    InputStream is =
        processDiagramGenerator.generateDiagram(bpmnModel, "png", activiityIds, activiityIds,
            processEngineConfiguration.getActivityFontName(),
            processEngineConfiguration.getLabelFontName(), null,
            processEngineConfiguration.getClassLoader(), 1.0);

    return is;
  }

}
