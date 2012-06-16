package base.service;

import itemTracker.service.ItemTrackerServiceBI;
import project.service.ProjectServiceBI;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;

public class ServiceContainer {

	private static ServiceContainer instance;
	private ItemTrackerServiceBI itemTrackerService;
	private UserServiceBI userService;
	private ProjectServiceBI projectService;
	private TeamServiceBI teamService;

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
}
