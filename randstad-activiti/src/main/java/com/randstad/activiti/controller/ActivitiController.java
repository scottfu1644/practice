package com.randstad.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDiagramCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.randstad.activiti.controller.vo.GroupVO;
import com.randstad.activiti.controller.vo.UserVO;
import com.randstad.activiti.interceptor.ProcessInstanceDiagramCmd;

/**
 * 
 * Function: TODO(suzu) Add function Description. <br>
 * 
 * @author suzu
 */
@Controller
@RequestMapping("/process")
public class ActivitiController {

	@Resource
	ProcessEngine engine;

	/**
	 * activiti是不能直接识别xml格式的流程图的，流程图文件必须被部署到activiti中才能被activiti识别并使用
	 * 而这个过程就是使用repositoryService来完成的，流程图被导入后，会放到act_re_打头的几个表中
	 * repositoryService提供了若干个接口，既可以部署xml文件到数据库中，也可以从数据库检索特定流程图供处理
	 */
	@Resource
	RepositoryService repositoryService;

	/**
	 * activiti最重要的一个服务 基本上所有的关于流程的操作都是通过此服务来执行的。例如启动流程、审批、会签等等。
	 */
	@Resource
	RuntimeService runtimeService;

	/**
	 * 工作流的设计思路之一就是将每个节点需要显示的数据直接绑定到此节点
	 * 而formService就是专门为此服务的，使用formService可以获取某个节点绑定的表单数据
	 * 当然，如果没有表单绑定到此节点，此服务就没有任何用处
	 */
	@Resource
	FormService formService;

	/**
	 * activiti自带的用于管理自身的组织机构的服务
	 * activiti自身的组织机构包括user和group两大类，而user、group以及user和group的关系都是通过自服务来维护的
	 * 因此如果需要使用activiti自身的组织机构的，就会使用到此服务
	 */
	@Resource
	IdentityService identityService;

	/**
	 * 任务是activiti的核心功能之一，所有涉及到任务的操作都是通过此服务来完成的 例如任务的查询、分配、认领、完成等
	 */
	@Resource
	TaskService taskService;

	/**
	 * 所有流程实例的信息都会被保存的历史信息中 当一个流程实例结束之前，它是被保存在runtime和history两个地方
	 * 当它结束后，就只有history里了
	 */
	@Resource
	HistoryService historyService;

	/**
	 * 提供对activiti数据库的直接访问 因此通常情况下使用不到此服务
	 */
	@Resource
	ManagementService managementService;

	@RequestMapping("actModMgr")
	public String actModMgr() {
		return "activiti/actModMgr";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("creatMode")
	@ResponseBody
	public void creatMode(String modName,String modDesc) {
		try {
			Model newModel = repositoryService.newModel();
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modName);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(modDesc));
			newModel.setMetaInfo(modelObjectNode.toString());
			newModel.setName(modName);
			newModel.setKey(StringUtils.defaultString(modName));
			repositoryService.saveModel(newModel);
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			repositoryService.addModelEditorSource(newModel.getId(), editorNode.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列出所有流程模板
	 */
	@RequestMapping( value="/getActModelPage" )
	@ResponseBody
	public List<Model> list(int page , int size) {
		int start = 0;
		if(page > 0){
			start = (page - 1)*size;
		}
		List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().listPage(start, size);
		return list;
	}
	
	@RequestMapping("/getPageCount")
	@ResponseBody
	public Long getPageCount() {
		try {
			return repositoryService.createModelQuery().count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 部署流程
	 */
	@RequestMapping("deploy")
	public ModelAndView deploy(String modelId, ModelAndView mav) {
		try {
			if (null != modelId) {
				Model modelData = repositoryService.getModel(modelId);
				ObjectNode modelNode = (ObjectNode) new ObjectMapper()
						.readTree(repositoryService.getModelEditorSource(modelData.getId()));
				byte[] bpmnBytes = null;

				BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
				bpmnBytes = new BpmnXMLConverter().convertToXML(model);

				String processName = modelData.getName() + ".bpmn20.xml";
				Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
						.addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
				System.out.println(deployment.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();

		mav.addObject("list", list);
		mav.setViewName("process/deployed");
		return mav;
	}

	/**
	 * 已部署流程列表
	 */
	@RequestMapping("deployed")
	public ModelAndView deployed(ModelAndView mav) {

		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();

		mav.addObject("list", list);
		mav.setViewName("process/deployed");

		return mav;
	}

	/**
	 * 启动一个流程实例
	 */
	@RequestMapping("start")
	public ModelAndView start(String id, ModelAndView mav) {

		runtimeService.startProcessInstanceById(id);

		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();

		mav.addObject("list", list);
		mav.setViewName("process/started");

		return mav;
	}

	/**
	 * 所有已启动流程实例
	 */
	@RequestMapping("started")
	public ModelAndView started(ModelAndView mav) {

		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();

		mav.addObject("list", list);
		mav.setViewName("process/started");

		return mav;
	}

	@RequestMapping("task")
	public ModelAndView task(ModelAndView mav) {
		List<Task> list = taskService.createTaskQuery().list();
		mav.addObject("list", list);
		mav.setViewName("process/task");
		return mav;
	}

	@RequestMapping("complete")
	public ModelAndView complete(ModelAndView mav, String id) {

		taskService.complete(id);

		return new ModelAndView("redirect:task");
	}

	/**
	 * 所有已启动流程实例
	 * 
	 * @throws IOException
	 */
	@RequestMapping("graphics")
	public void graphics(String definitionId, String instanceId, String taskId, ModelAndView mav,
			HttpServletResponse response) throws IOException {

		response.setContentType("image/png");
		Command<InputStream> cmd = null;

		if (definitionId != null) {
			cmd = new GetDeploymentProcessDiagramCmd(definitionId);
		}

		if (instanceId != null) {
			cmd = new ProcessInstanceDiagramCmd(instanceId);
		}

		if (taskId != null) {
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			cmd = new ProcessInstanceDiagramCmd(task.getProcessInstanceId());
		}

		if (cmd != null) {
			InputStream is = managementService.executeCommand(cmd);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = is.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		}
	}

	/**
	 * 用户组-列表
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping("ugroup")
	public ModelAndView ugroup(ModelAndView mav) {
		List<Group> uGroups = identityService.createGroupQuery().list();
		identityService.createGroupQuery().listPage(0, 0);// 分页返回查询结果
		identityService.createGroupQuery().count();// 结果总数
		mav.addObject("ugroups", uGroups);
		mav.setViewName("process/ugroup");
		return mav;
	}

	/**
	 * 用户组-新增页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping("ugroupcreate")
	public ModelAndView ugroupCreate(ModelAndView mav) {
		mav.setViewName("process/ugroup_create");
		return mav;
	}

	/**
	 * 用户组-详情页面，包括显示此用户组下管理用户
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping("ugroupdetail")
	public ModelAndView ugroupdetail(String groupId, ModelAndView mav) {
		List<User> users = identityService.createUserQuery().memberOfGroup(groupId).list();
		Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
		mav.addObject("group", group);
		mav.addObject("users", users);
		mav.setViewName("process/ugroup_detail");
		return mav;
	}

	/**
	 * 用户组-保存操作
	 * 
	 * @param groupVO
	 * @param mav
	 * @return
	 */
	@RequestMapping("ugroupsave")
	public ModelAndView ugroupSave(GroupVO groupVO, ModelAndView mav) {
		Group group = identityService.newGroup(String.valueOf(System.currentTimeMillis()));
		group.setName(groupVO.getgName());
		group.setType(groupVO.getgType());
		identityService.saveGroup(group);
		mav.setViewName("process/ugroup_create");
		return mav;
	}

	/**
	 * 用户-列表
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping("user")
	public ModelAndView user(ModelAndView mav) {
		List<User> user = identityService.createUserQuery().list();
		mav.addObject("user", user);
		mav.setViewName("process/user");
		return mav;
	}

	/**
	 * 用户-新增页面
	 * 
	 * @param mav
	 * @return
	 */
	@RequestMapping("usercreate")
	public ModelAndView userCreate(ModelAndView mav) {
		mav.setViewName("process/user_create");
		return mav;
	}

	/**
	 * 用户-详情页面，包括用户所在组
	 * 
	 * @param userId
	 * @param mav
	 * @return
	 */
	@RequestMapping("userdetail")
	public ModelAndView userdetail(String userId, ModelAndView mav) {
		User user = identityService.createUserQuery().userId(userId).singleResult();
		Group groupContainsUser = identityService.createGroupQuery().groupMember(user.getId()).singleResult();
		List<Group> groups = identityService.createGroupQuery().list();
		mav.addObject("userGroup", groupContainsUser);
		mav.addObject("groups", groups);
		mav.addObject("user", user);
		mav.setViewName("process/user_detail");
		return mav;
	}

	/**
	 * 用户-保存用户
	 * 
	 * @param userVO
	 * @param mav
	 * @return
	 */
	@RequestMapping("usersave")
	public ModelAndView userSave(UserVO userVO, ModelAndView mav) {
		User user = identityService.newUser(String.valueOf(System.currentTimeMillis()));
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setPassword("123456");
		user.setEmail(userVO.geteMail());
		identityService.saveUser(user);
		mav.setViewName("process/user_create");
		return mav;
	}

	/**
	 * 用户-保存用户与用户组关系
	 * 
	 * @param userId
	 * @param groupId
	 * @param mav
	 * @return
	 */
	@RequestMapping("usersavegroup")
	public ModelAndView userSaveGroup(String userId, String groupId, ModelAndView mav) {
		identityService.createMembership(userId, groupId);
		User user = identityService.createUserQuery().userId(userId).singleResult();
		Group groupContainsUser = identityService.createGroupQuery().groupMember(user.getId()).singleResult();
		List<Group> groups = identityService.createGroupQuery().list();
		mav.addObject("userGroup", groupContainsUser);
		mav.addObject("groups", groups);
		mav.addObject("user", user);
		mav.setViewName("process/user_detail");
		return mav;
	}

}
