package workflow.test.transition;

import item.dto.itemType.ItemTypeDTO;
import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class TransitionCreationTest extends TransitionServiceTest {

	private ItemStateDTO anItemPendingStateDTO;
	private ItemStateDTO anItemInDevelopmentStateDTO;
	private TransitionDTO aTransitionDTO;
	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.aUserDTOForListCollection = this.createAUserCollection(TransitionCreationTest.class.toString());
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.NEW_TEAM_NAME);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME, this.aTeamDTO, this.aWorkflowDTO);
		this.anItemPendingStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.PENDING, true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.anItemInDevelopmentStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO,
				TestConstants.IN_DEVELOPMENT, false);
	}

	@Override
	public void tearDown() throws Exception {
		this.anItemPendingStateDTO = this.getItemStateDTO(this.anItemPendingStateDTO);
		this.anItemInDevelopmentStateDTO = this.getItemStateDTO(this.anItemInDevelopmentStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);

		this.removeTransition(this.anItemPendingStateDTO, this.aTransitionDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, anItemInDevelopmentStateDTO);
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, anItemPendingStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(TransitionCreationTest.class.toString());
	}

	public void testCreateTransition() {
		this.aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getTransitionCode(), TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getName(), TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getNextState().getOid(), this.anItemInDevelopmentStateDTO.getOid());

	}
}
