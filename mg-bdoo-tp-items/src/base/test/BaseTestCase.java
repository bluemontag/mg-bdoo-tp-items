package base.test;

import itemTracker.service.ItemTrackerServiceBI;
import junit.framework.TestCase;

import org.junit.Before;

import project.service.ProjectServiceBI;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;
import workflow.service.WorkflowServiceBI;
import base.service.ServiceContainer;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseTestCase extends TestCase {

	protected String sessionToken;

	protected ItemTrackerServiceBI itemTrackerService;
	protected UserServiceBI userService;
	protected ProjectServiceBI projectService;
	protected TeamServiceBI teamService;
	protected WorkflowServiceBI workflowService;

	@Override
	@Before
	public void setUp() throws Exception {

		// se guardan los servicios para que sea mas corta la sentencia.
		this.itemTrackerService = ServiceContainer.getInstance().getItemTrackerService();
		this.userService = ServiceContainer.getInstance().getUserService();
		this.projectService = ServiceContainer.getInstance().getProjectService();
		this.teamService = ServiceContainer.getInstance().getTeamService();
		this.workflowService = ServiceContainer.getInstance().getWorkflowService();

		// se loggea en la aplicacion
		this.sessionToken = this.itemTrackerService.loginUser(TestConstants.ADMIN_USER_NAME,
				TestConstants.ADMIN_PASSWORD);

	}

}
