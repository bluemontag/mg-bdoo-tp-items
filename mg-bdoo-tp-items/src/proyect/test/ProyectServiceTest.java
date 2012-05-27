package proyect.test;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import proyect.dto.ProyectDTO;
import proyect.service.ProyectServiceBI;
import user.dto.UserDTO;
import user.service.UserServiceBI;

import base.service.ServiceFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class ProyectServiceTest extends TestCase{

	protected ProyectDTO aCreatedProyectDTO;
	protected UserDTO aCreatedProyectLeaderUserDTO;
	
	protected ProyectServiceBI proyectService;
	protected UserServiceBI userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.proyectService = ServiceFinder.getInstance().getProyectService();
		this.userService = ServiceFinder.getInstance().getUserService();
	}

	@After
	public void tearDown() throws Exception {
	}
}
