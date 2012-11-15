package base.service;

import item.service.ItemServiceBI;
import item.service.itemType.ItemTypeServiceBI;
import itemTracker.service.ItemTrackerServiceBI;
import project.service.ProjectServiceBI;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;
import workflow.service.WorkflowServiceBI;
import workflow.service.state.ItemStateServiceBI;

public class ServiceContainer {

	private static ServiceContainer instance;
	private ItemTrackerServiceBI itemTrackerService;
	private UserServiceBI userService;
	private ProjectServiceBI projectService;
	private TeamServiceBI teamService;
	private WorkflowServiceBI workflowService;
	private ItemServiceBI itemService;
	private ItemStateServiceBI itemStateService;
	private ItemTypeServiceBI itemTypeService;
	
	/**
	 * Método estático que permite acceder a la única instancia de esta clase.
	 * 
	 * @return la única instancia de esta clase.
	 */
	public static ServiceContainer getInstance() {
		if (instance == null) {
			instance = new ServiceContainer();
		}
		return instance;
	}

	public ItemTrackerServiceBI getItemTrackerService() {
		return itemTrackerService;
	}

	public void setItemTrackerService(ItemTrackerServiceBI itemTrackerService) {
		this.itemTrackerService = itemTrackerService;
	}

	public UserServiceBI getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBI userService) {
		this.userService = userService;
	}

	public ProjectServiceBI getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectServiceBI userService) {
		this.projectService = userService;
	}

	public TeamServiceBI getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamServiceBI teamService) {
		this.teamService = teamService;
	}

	public void setWorkflowService(WorkflowServiceBI workflowService) {
		this.workflowService = workflowService;
	}

	public WorkflowServiceBI getWorkflowService() {
		return workflowService;
	}

	public ItemServiceBI getItemService() {
		return itemService;
	}

	public void setItemService(ItemServiceBI itemService) {
		this.itemService = itemService;
	}

	public ItemStateServiceBI getItemStateService() {
		return itemStateService;
	}

	public void setItemStateService(ItemStateServiceBI itemStateService) {
		this.itemStateService = itemStateService;
	}

	public ItemTypeServiceBI getItemTypeService() {
		return itemTypeService;
	}

	public void setItemTypeService(ItemTypeServiceBI itemTypeService) {
		this.itemTypeService = itemTypeService;
	}
}
