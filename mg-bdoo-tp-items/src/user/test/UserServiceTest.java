package user.test;

import itemTracker.service.ItemTrackerServiceBI;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import user.service.UserServiceBI;
import base.service.ServiceContainer;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class UserServiceTest extends TestCase {

	protected UserServiceBI userService;
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
		this.userService = ServiceContainer.getInstance().getUserService();
		this.itemTrackerService = ServiceContainer.getInstance().getItemTrackerService();
		this.sessionToken = this.itemTrackerService.loginUser("rodrigo", "rodrigo");
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}
}
