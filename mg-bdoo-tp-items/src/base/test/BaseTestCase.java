package base.test;

import item.dto.ItemDTO;
import item.dto.itemType.ItemTypeDTO;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import item.exception.itemType.UnknownItemTypeException;
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
import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
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

	protected Collection<UserDTOForLists> aUserDTOForListCollection = new HashSet<UserDTOForLists>();
	protected UserDTO anUserDTO;
	protected WorkflowDTO aWorkflowDTO;
	protected TeamDTO aTeamDTO;
	protected ItemTypeDTO anItemTypeDTO;
	protected ItemDTO anItemDTO;

	protected ItemStateDTO anItemStateDTO;

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
	protected void removeTheUserCollection() {
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
			this.anUserDTO = this.userService.createUser(this.sessionToken, TestConstants.NEW_USER_NAME, "anyPassword");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteUser() {
		try {
			UserDTO anUserDTOToRemove = this.userService.getUser(this.sessionToken, this.anUserDTO);
			this.userService.removeUser(this.sessionToken, anUserDTOToRemove);
		} catch (UnknownUserException e) {
			fail("El usuario que desea eliminar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El usuario que intenta eliminar fue modificado por otro usuario.");
		}
	}

	protected void createItemStateOnWorkflow(String name, boolean isFirstSatate) {
		try {
			this.anItemStateDTO = this.itemStateService.createItemStateOnWorkflow(this.sessionToken, aWorkflowDTO,
					name, isFirstSatate);
		} catch (ItemStateAlreadyExistsException e) {
			fail("EL estado " + name + " ya existe en el workflow " + aWorkflowDTO.getName());
		} catch (UnknownWorkflowException e) {
			fail("EL workflow " + aWorkflowDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: No deberia pasar en un test.");
		}
	}

	protected void removeItemStateFromWorkflow(ItemStateDTO anItemStateDTO) {
		try {
			this.itemStateService.removeItemStateFromWorkflow(sessionToken, this.aWorkflowDTO, anItemStateDTO);
		} catch (UnknownItemStateException e) {
			fail("No se puede eliminar el estado" + this.anItemStateDTO.getName() + " ya que no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia tirar esta excepcion en un test controlado.");
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + this.aWorkflowDTO.getName());
		}
	}

	protected void createWorkflow() {
		try {
			this.aWorkflowDTO = this.workflowService.createWorkflow(this.sessionToken, TestConstants.WORKFLOW_NAME);
		} catch (WorkflowAlreadyExistsException e) {
			fail("El workflow " + TestConstants.WORKFLOW_NAME + " ya existe");
		}
	}

	protected void refreshWorkflow() {
		try {
			this.aWorkflowDTO = this.workflowService.getWorkflowByDTO(sessionToken, this.aWorkflowDTO);
		} catch (UnknownWorkflowException e) {
			fail("El workflow " + this.aWorkflowDTO.getName() + " no existe.");
		}
	}

	protected void removeWorkflow() {
		try {
			this.workflowService.removeWorkflow(this.sessionToken, this.aWorkflowDTO);
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + this.aWorkflowDTO.getName());
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir esto.");
		}
	}

	protected void createTeam() {
		try {
			this.aTeamDTO = this.teamService.createTeam(this.sessionToken, TestConstants.NEW_TEAM_NAME,
					this.aUserDTOForListCollection);
		} catch (TeamAlreadyExistsException e) {
			fail("El equipo que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios que se quieren setear no existe.");
		}
	}

	protected void removeTeam() {
		try {
			TeamDTO aTeamDTO = this.teamService.getTeam(this.sessionToken, this.aTeamDTO);
			this.teamService.removeTeam(this.sessionToken, aTeamDTO);
		} catch (DTOConcurrencyException e) {
			fail("El equipo que intenta eliminar fue modificado por otro usuario.");
		} catch (UnknownTeamException e) {
			fail("El equipo que se desea eliminar no existe.");
		}
	}

	protected void createItemType() {
		try {
			this.anItemTypeDTO = this.itemTypeService.createItemType(sessionToken, TestConstants.ITEM_TYPE_NAME,
					this.aTeamDTO, this.aWorkflowDTO);
		} catch (ItemTypeAlreadyExistsException e) {
			fail("El tipo de item " + TestConstants.ITEM_TYPE_NAME + " ya existe.");
		} catch (UnknownTeamException e) {
			fail("El equipo " + this.aTeamDTO.getName() + " no existe.");
		} catch (UnknownWorkflowException e) {
			fail("El workflow " + this.aWorkflowDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El equipo que intenta eliminar fue modificado por otro usuario.");
		}
	}

	protected void removeItemType() {
		try {
			this.itemTypeService.removeItemType(sessionToken, this.anItemTypeDTO);
		} catch (UnknownItemTypeException e) {
			fail("El tipo de item " + this.anItemTypeDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		}
	}

	protected void createItem() {
		try {
			this.anItemDTO = this.itemService.createItem(sessionToken, TestConstants.ITEM_DESCRIPTION,
					TestConstants.PRIORITY, this.anItemTypeDTO);
		} catch (ItemAlreadyExistsException e1) {
			fail("El item que desea crear ya existe.");
		} catch (UnknownItemTypeException e1) {
			fail("El typo de item seleccionado no existe.");
		}
	}

	protected void removeItem() {
		try {
			this.itemService.removeItem(sessionToken, this.anItemDTO);
		} catch (UnknownItemException e) {
			fail("El item numero " + this.anItemDTO.getItemNum() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir.");
		}

	}
}
