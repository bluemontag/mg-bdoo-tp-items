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
			itemTypeDTO = this.getItemTypeService().createItemType(this.sessionToken, TestConstants.ITEM_TYPE_1,
					aTeamDTO, aWorkFlowDTO);
		} catch (UnknownWorkflowException e) {
			fail("UnknownWorkflow");
		} catch (ItemTypeAlreadyExistsException e) {
			fail("Item existe");
		} catch (UnknownTeamException e) {
			fail("Team desconocido");
		}

		assertEquals(TestConstants.ITEM_TYPE_1, itemTypeDTO.getName());
		assertEquals(aWorkFlowDTO, itemTypeDTO.getWorkflowDTO());
	}

	@Override
	protected void tearDown() throws Exception {
		itemTypeDTO = this.getItemTypeService().getItemType(this.sessionToken, itemTypeDTO);
		this.getItemTypeService().removeItemType(this.sessionToken, itemTypeDTO);

		this.aWorkFlowDTO = this.getWorkflowService().getWorkflowByDTO(this.sessionToken, aWorkFlowDTO);
		this.getWorkflowService().removeWorkflow(this.sessionToken, this.aWorkFlowDTO);

		this.aTeamDTO = this.getTeamService().getTeam(this.sessionToken, aTeamDTO);
		this.getTeamService().removeTeam(this.sessionToken, aTeamDTO);

		this.deleteTheUserCollection();
		super.tearDown();
	}

	// ////// AUXILIARES ///////////

	protected void setInitialStateForWorkFlow(ItemStateDTO aWorkFlowfirstStateDTO) {
		// Seteo el estado inicial al WF
		try {
			this.getWorkflowService().setInitialState(this.sessionToken, aWorkFlowDTO, aWorkFlowfirstStateDTO);
		} catch (UnknownWorkflowException e) {
			System.out.println("El workflow es desconocido.");
		} catch (UnknownItemStateException e) {
			System.out.println("El estado inicial es desconocido.");
		}
	}

	protected void createAWorkFlow() throws WorkflowAlreadyExistsException {
		aWorkFlowDTO = this.getWorkflowService().createWorkflow(this.sessionToken, TestConstants.WORKFLOW_NAME);
	}

	protected void createATeam() throws UnknownUserException, TeamAlreadyExistsException {
		aTeamDTO = this.getTeamService().createTeam(this.sessionToken, TestConstants.TEAM_ITEM_TYPE,
				this.aUserDTOForListCollection);
	}

	// Devuelve el primer estado del WorkFlow
	protected ItemStateDTO createStatesForWorkFlow() {
		// agrego estados
		ItemStateDTO aStatePendingDTO = this.createOrGetItemState(TestConstants.PENDIENTE);
		ItemStateDTO aStateInDevelopmentDTO = this.createOrGetItemState(TestConstants.IN_DEVELOPMENT);
		ItemStateDTO aStateFinalDTO = this.createOrGetItemState(TestConstants.FINAL);

		// Intento setear los proximos estados
		this.addNextState(aStatePendingDTO, aStateInDevelopmentDTO);
		this.addNextState(aStateInDevelopmentDTO, aStateFinalDTO);
		this.addNextState(aStatePendingDTO, aStateFinalDTO);
		return aStatePendingDTO;
	}
}
