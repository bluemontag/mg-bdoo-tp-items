package base.test;

import item.service.ItemServiceBI;
import item.service.itemType.ItemTypeServiceBI;
import itemTracker.service.ItemTrackerServiceBI;

import java.util.Collection;
import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.Before;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import project.service.ProjectServiceBI;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.service.UserServiceBI;
import user.service.team.TeamServiceBI;
import workflow.dto.state.ItemStateDTO;
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
	
	// propiedades usadas por algunos tests.
	protected Collection<UserDTOForLists> aUserDTOForListCollection = new HashSet<UserDTOForLists>();
	protected UserDTO aCreatedUserDTO;

	private final static String CONTEXT = "applicationContext.xml";
	private AbstractApplicationContext ctx; 
	
	
	protected void setServices() {
		//levanto el contexto del xml
		String[] contextPaths = new String[] { BaseTestCase.CONTEXT };
		ctx = new ClassPathXmlApplicationContext(contextPaths);

		UserServiceBI userService = (UserServiceBI)ctx.getBean("userService");
		this.setUserService(userService);
		
		WorkflowServiceBI wfService = (WorkflowServiceBI) ctx.getBean("workflowService");
		this.setWorkflowService(wfService);

		ItemStateServiceBI itemStateService = (ItemStateServiceBI) ctx.getBean("itemStateService");
		this.setItemStateService(itemStateService);
		
		TeamServiceBI teamService = (TeamServiceBI)ctx.getBean("teamService");
		this.setTeamService(teamService);
		
		ItemTypeServiceBI itService = (ItemTypeServiceBI)ctx.getBean("itemTypeService");
		this.setItemTypeService(itService);
		
		ItemTrackerServiceBI itemTrackerService = (ItemTrackerServiceBI)ctx.getBean("itemTrackerService");
		this.setItemTrackerService(itemTrackerService);
	}
	
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

	protected ItemStateDTO getItemState(String name) {
		ItemStateDTO stateDTO = null;
		try {
			stateDTO = this.getItemStateService().createItemState(this.sessionToken, name);
		} catch (ItemStateAlreadyExistsException e) {
			try {
				stateDTO = this.getItemStateService().getItemStateByName(this.sessionToken, name);
			} catch (UnknownItemStateException e1) {
				e1.printStackTrace();
				fail("No se pudo recuperar o crear el estado: no existe el estado:"+name);
			}
		}
		return stateDTO;
	}
	
	protected void addNextState(ItemStateDTO parent, ItemStateDTO child) {
		try {
			this.getItemStateService().addNextState(this.sessionToken, parent, child);
		} catch (UnknownItemStateException e) {
			e.printStackTrace();
			fail("No se pudo setear el proximo estado: no existe el estado "+parent.getOid()+" o el estado "+child.getOid());
		} catch (DTOConcurrencyException e) {
			e.printStackTrace();
			fail("Error de concurrencia al tratar de setear proximo estado");
		}
	}
	/**
	 * @return the itemService
	 */
	public ItemServiceBI getItemService() {
		return itemService;
	}

	/**
	 * @param itemService the itemService to set
	 */
	public void setItemService(ItemServiceBI itemService) {
		this.itemService = itemService;
	}

	public WorkflowServiceBI getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowServiceBI workflowService) {
		this.workflowService = workflowService;
	}

	public ItemStateServiceBI getItemStateService() {
		return itemStateService;
	}

	public void setItemStateService(ItemStateServiceBI itemStateService) {
		this.itemStateService = itemStateService;
	}

	public TeamServiceBI getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamServiceBI teamService) {
		this.teamService = teamService;
	}

	public UserServiceBI getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBI userService) {
		this.userService = userService;
	}

	public ItemTrackerServiceBI getItemTrackerService() {
		return itemTrackerService;
	}

	public void setItemTrackerService(ItemTrackerServiceBI itemTrackerService) {
		this.itemTrackerService = itemTrackerService;
	}

	public ItemTypeServiceBI getItemTypeService() {
		return itemTypeService;
	}

	public void setItemTypeService(ItemTypeServiceBI itemTypeService) {
		this.itemTypeService = itemTypeService;
	}
}
