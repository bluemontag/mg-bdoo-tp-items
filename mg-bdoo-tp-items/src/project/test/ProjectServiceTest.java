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
import project.service.ProjectServiceBI;
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

	protected ProjectDTO aCreatedProjectDTO;
	protected UserDTO aCreatedProjectLeaderUserDTO;

	protected ProjectServiceBI projectService;
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
		this.projectService = ServiceContainer.getInstance().getProjectService();
		this.userService = ServiceContainer.getInstance().getUserService();
		this.sessionToken = this.itemTrackerService.loginUser(TestConstants.ADMIN_USER_NAME,
				TestConstants.ADMIN_PASSWORD);
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	protected void createProject() {
		try {
			// se crea el proyecto que se va a updetear
			this.aCreatedProjectDTO = this.projectService.createProject(this.sessionToken,
					TestConstants.NEW_PROJECT_NAME, this.aCreatedProjectLeaderUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
	}

	protected void createUserProjectLeader() {
		try {
			// se crea un usuario para setear como lider del proyecto.
			this.aCreatedProjectLeaderUserDTO = this.userService.createUser(this.sessionToken,
					TestConstants.NEW_USER_NAME, "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteCreatedUserProjectLeader() throws UnknownUserException, DTOConcurrencyException {
		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		UserDTO anUserDTOToRemove = this.userService.getUser(this.sessionToken, this.aCreatedProjectLeaderUserDTO);
		this.userService.removeUser(this.sessionToken, anUserDTOToRemove);
	}

	protected void deleteCreatedProject() throws UnknownProjectException, DTOConcurrencyException {
		ProjectDTO aProjectDTOToRemove = this.projectService.getProject(this.sessionToken, this.aCreatedProjectDTO);
		// Se elimina el proyecto para dejar la base como estaba.
		this.projectService.removeProject(this.sessionToken, aProjectDTOToRemove);
	}
}
