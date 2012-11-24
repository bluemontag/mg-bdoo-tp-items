package item.test;

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
public class ItemTransitionTest extends ItemServiceTest {

	private ItemStateDTO anItemPendingStateDTO;
	private ItemStateDTO anItemInDevelopmentStateDTO;
	private TransitionDTO aTransitionDTO;
	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ItemTransitionTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);
		this.anItemPendingStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.PENDING_STATE,
				true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_DEVELOPMENT_STATE, false);
		this.aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		this.anItemTypeDTO = this.getItemTypeDTO(this.anItemTypeDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION + testCode, TestConstants.PRIORITY,
				this.anItemTypeDTO);
	}

	@Override
	public void tearDown() throws Exception {
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);
		this.anItemPendingStateDTO = this.getItemStateDTO(this.anItemPendingStateDTO);

		this.removeTransition(this.anItemPendingStateDTO, this.aTransitionDTO);
		this.removeItem(this.anItemDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, anItemInDevelopmentStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, anItemPendingStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testExecuteTransition() {

		try {
			this.itemService.executeTransition(sessionToken, this.anItemDTO, this.aTransitionDTO.getTransitionCode());
		} catch (BadTransitionException e) {
			fail("La transicion de codigo " + this.aTransitionDTO.getTransitionCode()
					+ "existe para el estado actual del item.");
		} catch (UnknownItemException e) {
			fail("El item que intenta modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("DTOConcurrencyException: no deberia pasar.");
		} catch (UserNotLoggedException e) {
			fail("Algo muy malo paso!");
		}
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		assertEquals(this.anItemDTO.getCurrentState(), this.anItemInDevelopmentStateDTO.getName());
	}
}