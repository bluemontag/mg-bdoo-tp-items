/**
 * @author igallego 
 */
package workflow.test.transition;

import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import workflow.exception.state.UnknownItemStateException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class TransitionCreationTest extends TransitionServiceTest {

	protected ItemStateDTO anItemPendingStateDTO;
	protected ItemStateDTO anItemInDevelopmentStateDTO;
	protected TransitionDTO aTransitionDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
		this.createTeam();
		this.createWorkflow();
		this.createItemType();
		this.createItemStateOnWorkflow(TestConstants.PENDING, true);
		this.anItemPendingStateDTO = this.anItemStateDTO;
		this.refreshWorkflow();
		this.createItemStateOnWorkflow(TestConstants.IN_DEVELOPMENT, false);
		this.anItemInDevelopmentStateDTO = this.anItemStateDTO;
	}

	@Override
	public void tearDown() throws Exception {
		this.refreshItemState();
		this.refreshWorkflow();

		this.removeTransition(this.anItemPendingStateDTO, this.aTransitionDTO);
		this.removeItemStateFromWorkflow(anItemInDevelopmentStateDTO);
		this.removeItemStateFromWorkflow(anItemPendingStateDTO);
		this.refreshWorkflow();
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
	}

	public void testCreateTransition() {
		this.aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getTransitionCode(), TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getName(), TestConstants.TRANSITION_IN_DEVELOPMENT);
		assertEquals(aTransitionDTO.getNextState().getOid(), this.anItemInDevelopmentStateDTO.getOid());

	}

	@Override
	protected void refreshItemState() {
		try {
			this.anItemInDevelopmentStateDTO = this.itemStateService.getItemStateByDTO(sessionToken,
					this.anItemInDevelopmentStateDTO);
			this.anItemPendingStateDTO = this.itemStateService.getItemStateByDTO(sessionToken,
					this.anItemPendingStateDTO);
		} catch (UnknownItemStateException e) {
			fail("Error al actualizar la instancia del itemState.");
		}
	}
}
