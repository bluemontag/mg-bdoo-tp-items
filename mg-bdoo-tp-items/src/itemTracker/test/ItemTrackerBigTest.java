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

	private ItemStateDTO anItemCreatedStateDTO;
	private ItemStateDTO anItemInDevelopmentStateDTO;
	private ItemStateDTO anItemInValidationStateDTO;
	private ItemStateDTO anItemFixedStateDTO;
	private TransitionDTO aTransitionFromCreatedToInDevelopmentDTO;
	private TransitionDTO aTransitionFromInDevelopmentToInValidationDTO;
	private TransitionDTO aTransitionFromInValidationToInDevelopmentDTO;
	private TransitionDTO aTransitionFromInValidationToFixedDTO;
	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ItemTrackerBigTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);
		this.createAllItemStates();
		this.createAllTransitions();
		this.anItemTypeDTO = this.getItemTypeDTO(this.anItemTypeDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION + testCode, TestConstants.PRIORITY,
				this.anItemTypeDTO);
	}

	@Override
	public void tearDown() throws Exception {
		this.anItemDTO = this.getItemDTO(this.anItemDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemCreatedStateDTO = this.getItemStateDTO(this.anItemCreatedStateDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);

		// this.removeTransition(this.anItemCreatedStateDTO,
		// this.aTransitionFromPendingToInDevelopmentDTO);
		this.removeItem(this.anItemDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemInDevelopmentStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemCreatedStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testExecuteTransition() {

		// executeTransition(this.anItemDTO,
		// this.aTransitionFromPendingToInDevelopmentDTO);
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

	protected void createAllTransitions() {
		this.aTransitionFromCreatedToInDevelopmentDTO = this.createTransition(this.anItemCreatedStateDTO,
				this.anItemInDevelopmentStateDTO, TestConstants.TRANSITION_IN_DEVELOPMENT,
				TestConstants.TRANSITION_IN_DEVELOPMENT);

		this.anItemCreatedStateDTO = this.getItemStateDTO(this.anItemCreatedStateDTO);

		this.aTransitionFromInDevelopmentToInValidationDTO = this.createTransition(this.anItemInDevelopmentStateDTO,
				this.anItemInValidationStateDTO, TestConstants.TRANSITION_IN_VALIDATION,
				TestConstants.TRANSITION_IN_VALIDATION);

		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);

		this.aTransitionFromInValidationToInDevelopmentDTO = this.createTransition(this.anItemInValidationStateDTO,
				this.anItemInDevelopmentStateDTO, TestConstants.TRANSITION_REJECT, TestConstants.TRANSITION_REJECT);

		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);

		this.aTransitionFromInValidationToFixedDTO = this.createTransition(this.anItemInValidationStateDTO,
				this.anItemFixedStateDTO, TestConstants.TRANSITION_ACCEPT, TestConstants.TRANSITION_ACCEPT);

		this.anItemInValidationStateDTO = this.getItemStateDTO(this.anItemInValidationStateDTO);
	}

	protected ItemStateDTO getTransition(ItemStateDTO anItemInValidationStateDTO2) {

		return null;
	}

	protected void createAllItemStates() {
		this.anItemCreatedStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.CREATED_STATE,
				true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_DEVELOPMENT_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInValidationStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_VALIDATION_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemFixedStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.FIXED_STATE, false);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
	}
}
