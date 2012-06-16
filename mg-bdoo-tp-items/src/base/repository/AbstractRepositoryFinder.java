package base.repository;

import itemTracker.repository.ItemTrackerRepositoryBI;
import project.repository.ProjectRepositoryBI;
import user.repository.UserRepositoryBI;
import user.repository.team.TeamRepositoryBI;
import workflow.repository.WorkflowRepositoryBI;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractRepositoryFinder {

	private ItemTrackerRepositoryBI itemTrackerRepository;
	private UserRepositoryBI userRepository;
	private ProjectRepositoryBI projectRepository;
	private TeamRepositoryBI teamRepository;
	private WorkflowRepositoryBI workflowRepository;

	public ItemTrackerRepositoryBI getItemTrackerRepository() {
		return this.itemTrackerRepository;
	}

	public void setItemTrackerRepository(ItemTrackerRepositoryBI itemTrackerRepository) {
		this.itemTrackerRepository = itemTrackerRepository;
	}

	public UserRepositoryBI getUserRepository() {
		return this.userRepository;
	}

	public void setUserRepository(UserRepositoryBI userRepository) {
		this.userRepository = userRepository;
	}

	public ProjectRepositoryBI getProjectRepository() {
		return this.projectRepository;
	}

	public void setProjectRepository(ProjectRepositoryBI projectRepository) {
		this.projectRepository = projectRepository;
	}

	public TeamRepositoryBI getTeamRepository() {
		return this.teamRepository;
	}

	public void setTeamRepository(TeamRepositoryBI teamRepository) {
		this.teamRepository = teamRepository;
	}

	public WorkflowRepositoryBI getWorkflowRepository() {
		return this.workflowRepository;
	}

	public void setWorkflowRepository(WorkflowRepositoryBI workflowRepository) {
		this.workflowRepository = workflowRepository;
	}

}
