package user.test.team;

import itemTracker.service.ItemTrackerServiceBI;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import user.service.team.TeamServiceBI;
import base.service.ServiceContainer;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class TeamServiceTest extends TestCase {

	protected TeamServiceBI teamService;
	protected ItemTrackerServiceBI itemTrackerService;
	protected String sessionToken;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	@Before
	public void setUp() throws Exception {
		this.teamService = ServiceContainer.getInstance().getTeamService();
		this.itemTrackerService = ServiceContainer.getInstance().getItemTrackerService();
		this.sessionToken = this.itemTrackerService.loginUser(TestConstants.ADMIN_USER_NAME,
				TestConstants.ADMIN_PASSWORD);
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}
}
