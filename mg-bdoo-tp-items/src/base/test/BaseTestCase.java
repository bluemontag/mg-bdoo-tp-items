package base.test;

import item.dto.ItemDTO;
import item.dto.historicItem.HistoricItemDTOForLists;
import item.dto.itemType.ItemTypeDTO;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import item.exception.itemType.UnknownItemTypeException;
import item.service.ItemServiceBI;
import item.service.itemType.ItemTypeServiceBI;
import itemTracker.service.ItemTrackerServiceBI;

import java.util.ArrayList;
import java.util.Collection;

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
import workflow.dto.transition.TransitionDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import workflow.exception.transition.UnknownTransitionException;
import workflow.service.WorkflowServiceBI;
import workflow.service.state.ItemStateServiceBI;
import base.exception.DTOConcurrencyException;
import base.exception.UserNotLoggedException;
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

	protected Collection<UserDTOForLists> aUserDTOForListCollection = new ArrayList<UserDTOForLists>();

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

	protected TeamDTO getTeamDTO(TeamDTO aTeamDTO) {
		TeamDTO aTeamDTOAux = null;
		try {
			aTeamDTOAux = this.teamService.getTeam(sessionToken, aTeamDTO);
		} catch (UnknownTeamException e) {
			fail("Error al actualizar la instancia del equipo.");
		}
		return aTeamDTOAux;

	}

	protected WorkflowDTO getWorkflowDTO(WorkflowDTO aWorkflowDTO) {
		WorkflowDTO aWorkflowDTOAux = null;
		try {
			aWorkflowDTOAux = this.workflowService.getWorkflowByDTO(sessionToken, aWorkflowDTO);
		} catch (UnknownWorkflowException e) {
			fail("Error al actualizar la instancia del workflow.");
		}
		return aWorkflowDTOAux;
	}

	protected ItemTypeDTO getItemTypeDTO(ItemTypeDTO anItemTypeDTO) {
		ItemTypeDTO anItemTypeDTOAux = null;
		try {
			anItemTypeDTOAux = this.itemTypeService.getItemType(sessionToken, anItemTypeDTO);
		} catch (UnknownItemTypeException e) {
			fail("Error al actualizar la instancia del itemType.");
		}
		return anItemTypeDTOAux;
	}

	protected ItemStateDTO getItemStateDTO(ItemStateDTO anItemStateDTO) {
		ItemStateDTO anItemStateDTOAux = null;
		try {
			anItemStateDTOAux = this.itemStateService.getItemStateByDTO(sessionToken, anItemStateDTO);
		} catch (UnknownItemStateException e) {
			fail("Error al actualizar la instancia del itemState.");
		}
		return anItemStateDTOAux;
	}

	protected ItemDTO getItemDTO(ItemDTO anItemDTO) {
		ItemDTO anItemDTOAux = null;
		try {
			anItemDTOAux = this.itemService.getItem(sessionToken, anItemDTO);
		} catch (UnknownItemException e) {
			fail("Error al actualizar la instancia del item.");
		}
		return anItemDTOAux;
	}

	protected Collection<UserDTOForLists> createAUserCollection(String test) {
		Collection<UserDTOForLists> aUserDTOForListCollection = new ArrayList<UserDTOForLists>();
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aCreatedUserDTO;
			try {
				aCreatedUserDTO = this.userService.createUser(this.sessionToken, test + i, "no importa");

				aUserDTOForListCollection.add(new UserDTOForLists(aCreatedUserDTO));
			} catch (UserAlreadyExistsException e) {
				fail("El usuario que se intenta crear " + (test + i) + " ya existe.");
			}
		}
		return aUserDTOForListCollection;
	}

	@SuppressWarnings("deprecation")
	protected void removeTheUserCollection(String test) {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aUserDTOToRemove;
			try {
				aUserDTOToRemove = this.userService.getUserByUserName(this.sessionToken, test + i);
				this.userService.removeUser(this.sessionToken, aUserDTOToRemove);
			} catch (UnknownUserException e) {
				fail("El usuario " + TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + "_" + i + " no existe.");
			} catch (DTOConcurrencyException e) {
				fail("Error de concurrencia de DTO: Esto no deberia pasar ya que es un test controlado.");
			}
		}
	}

	protected UserDTO createUser(String anUserName, String aPassword) {
		UserDTO anUserDTOAux = null;
		try {
			anUserDTOAux = this.userService.createUser(this.sessionToken, anUserName, aPassword);
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
		return anUserDTOAux;
	}

	@SuppressWarnings("deprecation")
	protected void deleteUser(UserDTO anUserDTO) {
		try {
			UserDTO anUserDTOToRemove = this.userService.getUser(this.sessionToken, anUserDTO);
			this.userService.removeUser(this.sessionToken, anUserDTOToRemove);
		} catch (UnknownUserException e) {
			fail("El usuario que desea eliminar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El usuario que intenta eliminar fue modificado por otro usuario.");
		}
	}

	protected ItemStateDTO createItemStateOnWorkflow(WorkflowDTO aWorkflowDTO, String aItemStateName,
			boolean isFirstSatate) {
		ItemStateDTO anItemStateDTO = null;
		try {
			anItemStateDTO = this.itemStateService.createItemStateOnWorkflow(this.sessionToken, aWorkflowDTO,
					aItemStateName, isFirstSatate);
		} catch (ItemStateAlreadyExistsException e) {
			fail("EL estado " + aItemStateName + " ya existe en el workflow " + aWorkflowDTO.getName());
		} catch (UnknownWorkflowException e) {
			fail("EL workflow " + aWorkflowDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: No deberia pasar en un test.");
		}
		return anItemStateDTO;
	}

	protected void removeItemStateFromWorkflow(WorkflowDTO aWorkflowDTO, ItemStateDTO anItemStateDTO) {
		try {
			this.itemStateService.removeItemStateFromWorkflow(sessionToken, aWorkflowDTO, anItemStateDTO);
		} catch (UnknownItemStateException e) {
			fail("No se puede eliminar el estado" + anItemStateDTO.getName() + " ya que no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia tirar esta excepcion en un test controlado.");
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + aWorkflowDTO.getName());
		}
	}

	protected WorkflowDTO createWorkflow(String aWorkflowName) {
		WorkflowDTO aWorkflowDTO = null;
		try {
			aWorkflowDTO = this.workflowService.createWorkflow(this.sessionToken, aWorkflowName);
		} catch (WorkflowAlreadyExistsException e) {
			fail("El workflow " + TestConstants.WORKFLOW_NAME + " ya existe");
		}
		return aWorkflowDTO;
	}

	protected void removeWorkflow(WorkflowDTO aWorkflowDTO) {
		try {
			this.workflowService.removeWorkflow(this.sessionToken, aWorkflowDTO);
		} catch (UnknownWorkflowException e) {
			fail("No existe el workflow de nombre " + aWorkflowDTO.getName());
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir esto.");
		}
	}

	protected TeamDTO createTeam(Collection<UserDTOForLists> aUserDTOForListCollection, String aTeamName) {
		TeamDTO aTeamDTO = null;
		try {
			aTeamDTO = this.teamService.createTeam(this.sessionToken, aTeamName, aUserDTOForListCollection);
		} catch (TeamAlreadyExistsException e) {
			fail("El equipo que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios que se quieren setear no existe.");
		}
		return aTeamDTO;
	}

	protected void removeTeam(TeamDTO aTeamDTO) {
		try {
			this.teamService.removeTeam(this.sessionToken, aTeamDTO);
		} catch (DTOConcurrencyException e) {
			fail("El equipo que intenta eliminar fue modificado por otro usuario.");
		} catch (UnknownTeamException e) {
			fail("El equipo que se desea eliminar no existe.");
		}
	}

	protected ItemTypeDTO createItemType(String anItemTypeName, TeamDTO aTeamDTO, WorkflowDTO aWorkflowDTO) {
		ItemTypeDTO anItemTypeDTO = null;
		try {
			anItemTypeDTO = this.itemTypeService.createItemType(sessionToken, anItemTypeName, aTeamDTO, aWorkflowDTO);
		} catch (ItemTypeAlreadyExistsException e) {
			fail("El tipo de item " + TestConstants.ITEM_TYPE_NAME + " ya existe.");
		} catch (UnknownTeamException e) {
			fail("El equipo " + aTeamDTO.getName() + " no existe.");
		} catch (UnknownWorkflowException e) {
			fail("El workflow " + aWorkflowDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El equipo que intenta eliminar fue modificado por otro usuario.");
		}
		return anItemTypeDTO;
	}

	protected void removeItemType(ItemTypeDTO anItemTypeDTO) {
		try {
			this.itemTypeService.removeItemType(sessionToken, anItemTypeDTO);
		} catch (UnknownItemTypeException e) {
			fail("El tipo de item " + anItemTypeDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		}
	}

	protected ItemDTO createItem(String anItemDescription, Integer anItemPriority, ItemTypeDTO anItemTypeDTO) {
		ItemDTO anItemDTO = null;
		try {
			anItemDTO = this.itemService.createItem(sessionToken, anItemDescription, anItemPriority, anItemTypeDTO);
		} catch (ItemAlreadyExistsException e1) {
			fail("El item que desea crear ya existe.");
		} catch (UnknownItemTypeException e1) {
			fail("El typo de item seleccionado no existe.");
		} catch (UserNotLoggedException e) {
			fail("Algo muy malo paso.");
		}
		return anItemDTO;
	}

	protected void removeItem(ItemDTO anItemDTO) {
		try {
			this.itemService.removeItem(sessionToken, anItemDTO);
		} catch (UnknownItemException e) {
			fail("El item numero " + anItemDTO.getItemNum() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir.");
		}
	}

	protected TransitionDTO createTransition(ItemStateDTO anStartItemStateDTO, ItemStateDTO anItemEndStateDTO,
			String transitionName, String transitionCode) {
		TransitionDTO aTransitionDTO = null;
		try {
			aTransitionDTO = this.itemStateService.createTransitionOnItemState(sessionToken, anStartItemStateDTO,
					anItemEndStateDTO, transitionName, transitionCode);
		} catch (UnknownItemStateException e) {
			fail("Alguno de los estados que intenta relacionar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: Esto no deberia ocurrir.");
		}
		return aTransitionDTO;
	}

	protected void removeTransition(ItemStateDTO anItemStateDTO, TransitionDTO aTransitionDTO) {
		try {
			this.itemStateService.removeTransition(sessionToken, anItemStateDTO, aTransitionDTO);
		} catch (UnknownItemStateException e) {
			fail("El estado " + anItemStateDTO.getName() + " no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia ocurrir.");
		} catch (UnknownTransitionException e) {
			fail("La traniscion " + aTransitionDTO.getName() + " no existe en el estado " + anItemStateDTO.getName());
		}
	}

	protected Collection<? extends HistoricItemDTOForLists> listHistoricItem(ItemDTO anItemDTO) {
		Collection<? extends HistoricItemDTOForLists> historicItems = null;
		try {
			historicItems = this.itemService.listHistoricItems(sessionToken, anItemDTO);
		} catch (UnknownItemException e) {
			fail("El item numero " + anItemDTO.getItemNum() + " no existe.");
		}
		return historicItems;
	}
}
