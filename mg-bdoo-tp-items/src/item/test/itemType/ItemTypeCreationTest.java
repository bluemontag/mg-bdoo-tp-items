/**
 * @author igallego 
 */
package item.test.itemType;

import item.dto.itemType.ItemTypeDTO;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.UnknownWorkflowException;
import workflow.exception.WorkflowAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemTypeCreationTest extends ItemTypeServiceTest {

	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkFlowDTO;
	private ItemTypeDTO itemTypeDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp(); // crea los servicios y loguea usuario
		this.createAUserCollection();
		this.createATeam();
		this.createAWorkFlow();
		ItemStateDTO aWorkFlowfirstStateDTO = createStatesForWorkFlow();
		this.setInitialStateForWorkFlow(aWorkFlowfirstStateDTO);
	}

	public void testItemTypeCreation() throws UnknownUserException, TeamAlreadyExistsException,
			WorkflowAlreadyExistsException {

		try {
			itemTypeDTO = this.itemTypeService.createItemType(this.sessionToken, TestConstants.ITEM_TYPE_NAME,
					aTeamDTO, aWorkFlowDTO);
		} catch (UnknownWorkflowException e) {
			fail("UnknownWorkflow");
		} catch (ItemTypeAlreadyExistsException e) {
			fail("Item existe");
		} catch (UnknownTeamException e) {
			fail("Team desconocido");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		}

		assertEquals(TestConstants.ITEM_TYPE_NAME, itemTypeDTO.getName());
		assertEquals(aWorkFlowDTO, itemTypeDTO.getWorkflowDTO());
	}

	@Override
	protected void tearDown() throws Exception {
		itemTypeDTO = this.itemTypeService.getItemType(this.sessionToken, itemTypeDTO);
		this.itemTypeService.removeItemType(this.sessionToken, itemTypeDTO);

		this.aWorkFlowDTO = this.workflowService.getWorkflowByDTO(this.sessionToken, aWorkFlowDTO);
		this.workflowService.removeWorkflow(this.sessionToken, this.aWorkFlowDTO);

		this.aTeamDTO = this.teamService.getTeam(this.sessionToken, aTeamDTO);
		this.teamService.removeTeam(this.sessionToken, aTeamDTO);

		this.removeTheUserCollection();
		super.tearDown();
	}

	// ////// AUXILIARES ///////////

	protected void setInitialStateForWorkFlow(ItemStateDTO aWorkFlowfirstStateDTO) {
		// Seteo el estado inicial al WF
		try {
			this.workflowService.setInitialState(this.sessionToken, aWorkFlowDTO, aWorkFlowfirstStateDTO);
		} catch (UnknownWorkflowException e) {
			System.out.println("El workflow es desconocido.");
		} catch (UnknownItemStateException e) {
			System.out.println("El estado inicial es desconocido.");
		}
	}

	protected void createAWorkFlow() throws WorkflowAlreadyExistsException {
		aWorkFlowDTO = this.workflowService.createWorkflow(this.sessionToken, TestConstants.WORKFLOW_NAME);
	}

	protected void createATeam() throws UnknownUserException, TeamAlreadyExistsException {
		aTeamDTO = this.teamService.createTeam(this.sessionToken, TestConstants.TEAM_ITEM_TYPE,
				this.aUserDTOForListCollection);
	}

	// Devuelve el primer estado del WorkFlow
	protected ItemStateDTO createStatesForWorkFlow() {
		// agrego estados
		// ItemStateDTO aStatePendingDTO =
		// this.createOrGetItemState(TestConstants.PENDING);
		// ItemStateDTO aStateInDevelopmentDTO =
		// this.createOrGetItemState(TestConstants.IN_DEVELOPMENT);
		// ItemStateDTO aStateFinalDTO =
		// this.createOrGetItemState(TestConstants.FINAL);

		// Intento setear los proximos estados
		// this.addNextState(aStatePendingDTO, aStateInDevelopmentDTO);
		// this.addNextState(aStateInDevelopmentDTO, aStateFinalDTO);
		// this.addNextState(aStatePendingDTO, aStateFinalDTO);
		return null;
	}
}
