package project.test;

import itemTracker.service.ItemTrackerServiceBI;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import project.dto.ProjectDTO;
import project.exception.ProjectAlreadyExistsException;
import project.exception.UnknownProjectException;
import project.service.ProyectServiceBI;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.service.UserServiceBI;
import base.exception.DTOConcurrencyException;
import base.service.ServiceContainer;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class ProjectServiceTest extends TestCase {

	protected ProjectDTO aCreatedProyectDTO;
	protected UserDTO aCreatedProyectLeaderUserDTO;

	protected ProyectServiceBI proyectService;
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
		this.itemTrackerService = ServiceContainer.getInstance().getItemTrackerService();
		this.proyectService = ServiceContainer.getInstance().getProyectService();
		this.userService = ServiceContainer.getInstance().getUserService();
		this.sessionToken = this.itemTrackerService.loginUser(TestConstants.ADMIN_USER_NAME,
				TestConstants.ADMIN_PASSWORD);
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	protected void createProyect() {
		try {
			// se crea el proyecto que se va a updetear
			this.aCreatedProyectDTO = this.proyectService.createProyect(this.sessionToken,
					TestConstants.NEW_PROYECT_NAME, this.aCreatedProyectLeaderUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
	}

	protected void createUserProyectLeader() {
		try {
			// se crea un usuario para setear como lider del proyecto.
			this.aCreatedProyectLeaderUserDTO = this.userService.createUser(this.sessionToken,
					TestConstants.NEW_USER_NAME, "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteCreatedUserProyectLeader() throws UnknownUserException, DTOConcurrencyException {
		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		UserDTO anUserDTOToRemove = this.userService.getUser(this.sessionToken, this.aCreatedProyectLeaderUserDTO);
		this.userService.removeUser(this.sessionToken, anUserDTOToRemove);
	}

	protected void deleteCreatedProyect() throws UnknownProjectException, DTOConcurrencyException {
		ProjectDTO aProyectDTOToRemove = this.proyectService.getProyect(this.sessionToken, this.aCreatedProyectDTO);
		// Se elimina el proyecto para dejar la base como estaba.
		this.proyectService.removeProyect(this.sessionToken, aProyectDTOToRemove);
	}
}
