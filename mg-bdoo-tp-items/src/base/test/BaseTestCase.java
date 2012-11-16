package base.test;

import item.service.ItemServiceBI;
import item.service.itemType.ItemTypeServiceBI;
import itemTracker.service.ItemTrackerServiceBI;

import java.util.Collection;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Before;

import project.service.ProjectServiceBI;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.service.WorkflowServiceBI;
import workflow.service.state.ItemStateServiceBI;
import base.exception.DTOConcurrencyException;
import base.service.ServiceContainer;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseTestCase extends TestCase {

	// Token de autenticacion
	protected String sessionToken;

	// Servicios
	protected ItemTrackerServiceBI itemTrackerService;
	protected UserServiceBI userService;
	protected ProjectServiceBI projectService;
	protected TeamServiceBI teamService;
	protected WorkflowServiceBI workflowService;
	protected ItemServiceBI itemService;
	protected ItemStateServiceBI itemStateService;
	protected ItemTypeServiceBI itemTypeService;

	// propiedades usadas por algunos tests.
	protected Collection<UserDTOForLists> aUserDTOForListCollection = new HashSet<UserDTOForLists>();
	protected UserDTO aCreatedUserDTO;
	protected WorkflowDTO aWorkflowDTO;

	@Override
	@Before
	public void setUp() throws Exception {

		// se guardan los servicios para que sea mas corta la sentencia.
		this.itemTrackerService = ServiceContainer.getInstance().getItemTrackerService();
		this.userService = ServiceContainer.getInstance().getUserService();
		this.projectService = ServiceContainer.getInstance().getProjectService();
		this.teamService = ServiceContainer.getInstance().getTeamService();
		this.workflowService = ServiceContainer.getInstance().getWorkflowService();
		this.itemService = ServiceContainer.getInstance().getItemService();
		this.itemTypeService = ServiceContainer.getInstance().getItemTypeService();
		this.itemStateService = ServiceContainer.getInstance().getItemStateService();

		// se loggea en la aplicacion
		this.sessionToken = this.itemTrackerService.loginUser(TestConstants.ADMIN_USER_NAME,
				TestConstants.ADMIN_PASSWORD);

	}

	public ItemServiceBI getItemService() {
		return itemService;
	}

	public WorkflowServiceBI getWorkflowService() {
		return workflowService;
	}

	public ItemStateServiceBI getItemStateService() {
		return itemStateService;
	}

	public TeamServiceBI getTeamService() {
		return teamService;
	}

	public UserServiceBI getUserService() {
		return userService;
	}

	public ItemTrackerServiceBI getItemTrackerService() {
		return itemTrackerService;
	}

	public ItemTypeServiceBI getItemTypeService() {
		return itemTypeService;
	}

	protected void createAUserCollection() {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aCreatedUserDTO;
			try {
				aCreatedUserDTO = this.userService.createUser(this.sessionToken,
						TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i, "no importa");

				this.aUserDTOForListCollection.add(new UserDTOForLists(aCreatedUserDTO));
			} catch (UserAlreadyExistsException e) {
				fail("El usuario que se intenta crear " + (TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i)
						+ " ya existe.");
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteTheUserCollection() {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aUserDTOToRemove;
			try {
				aUserDTOToRemove = this.userService.getUserByUserName(this.sessionToken,
						TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i);
				this.userService.removeUser(this.sessionToken, aUserDTOToRemove);
			} catch (UnknownUserException e) {
				fail("El usuario " + TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + "_" + i + " no existe.");
			} catch (DTOConcurrencyException e) {
				fail("Error de concurrencia de DTO: Esto no deberia pasar ya que es un test controlado.");
			}
		}
	}

	protected void createUser() {
		try {
			this.aCreatedUserDTO = this.userService.createUser(this.sessionToken, TestConstants.NEW_USER_NAME,
					"anyPassword");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteCreatedUser() {
		try {
			UserDTO anUserDTOToRemove = this.userService.getUser(this.sessionToken, this.aCreatedUserDTO);
			this.userService.removeUser(this.sessionToken, anUserDTOToRemove);
		} catch (UnknownUserException e) {
			fail("El usuario que desea eliminar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El usuario que intenta eliminar fue modificado por otro usuario.");
		}
	}

	protected ItemStateDTO createItemStateOnWorkflow(WorkflowDTO aWorkflowDTO, String name, boolean firstSatate) {
		ItemStateDTO anItemStateDTO = null;
		try {
			anItemStateDTO = this.getItemStateService().createItemStateOnWorkflow(this.sessionToken, aWorkflowDTO,
					name, firstSatate);
		} catch (ItemStateAlreadyExistsException e) {
			fail("EL estado " + name + " ya existe en el workflow " + aWorkflowDTO.getName());
		} catch (UnknownWorkflowException e) {
			fail("EL workflow " + aWorkflowDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: No deberia pasar en un test.");
		}
		return anItemStateDTO;
	}

	protected void createWorkflow(String aWorkflowName) {
		try {
			this.aWorkflowDTO = this.getWorkflowService().createWorkflow(this.sessionToken, aWorkflowName);
		} catch (WorkflowAlreadyExistsException e) {
			fail("El workflow " + aWorkflowName + " ya existe");
		}
	}

	protected void eliminarWorkflow(WorkflowDTO aWorkflowDTO) {
		try {
			WorkflowDTO aWorkflowDTOAux = this.getWorkflowService().getWorkflowByDTO(this.sessionToken, aWorkflowDTO);
			this.getWorkflowService().removeWorkflow(this.sessionToken, aWorkflowDTOAux);
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + aWorkflowDTO.getName());
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir esto.");
		}
	}
}
