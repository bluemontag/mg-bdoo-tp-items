package itemTracker.test;

import item.dto.ItemDTO;
import item.dto.itemType.ItemTypeDTO;
import item.exception.UnknownItemException;
import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;
import base.exception.UserNotLoggedException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTrackerBigTest extends ItemTrackerTest {

	private ItemStateDTO anItemPendingStateDTO;
	private ItemStateDTO anItemInDevelopmentStateDTO;
	private TransitionDTO aTransitionDTO;
	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.aUserDTOForListCollection = this.createAUserCollection(ItemTrackerBigTest.class.toString());
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.NEW_TEAM_NAME);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME, this.aTeamDTO, this.aWorkflowDTO);
		this.anItemPendingStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.PENDING, true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_DEVELOPMENT, false);
		this.aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		this.anItemTypeDTO = this.getItemTypeDTO(this.anItemTypeDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION, TestConstants.PRIORITY, this.anItemTypeDTO);
	}

	@Override
	public void tearDown() throws Exception {
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemPendingStateDTO = this.getItemStateDTO(this.anItemPendingStateDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);

		this.removeTransition(this.anItemPendingStateDTO, this.aTransitionDTO);
		this.removeItem(this.anItemDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemInDevelopmentStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemPendingStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(ItemTrackerBigTest.class.toString());
	}

	public void testExecuteTransition() {

		executeTransition(this.anItemDTO, this.aTransitionDTO);
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInDevelopmentStateDTO.getName());
	}

	protected void executeTransition(ItemDTO anItemDTO, TransitionDTO aTransitionDTO) {
		try {
			this.itemService.executeTransition(sessionToken, anItemDTO, aTransitionDTO.getTransitionCode());
			this.anItemDTO = this.getItemDTO(this.anItemDTO);
		} catch (BadTransitionException e) {
			fail("La transicion de codigo " + aTransitionDTO.getTransitionCode()
					+ "existe para el estado actual del item.");
		} catch (UnknownItemException e) {
			fail("El item que intenta modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		} catch (UserNotLoggedException e) {
			fail("Algo muy malo paso!");
		}
	}
}
