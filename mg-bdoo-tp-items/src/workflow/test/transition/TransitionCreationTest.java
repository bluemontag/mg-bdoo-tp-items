/**
 * @author igallego 
 */
package workflow.test.transition;

import workflow.dto.state.ItemStateDTO;
import workflow.dto.transition.TransitionDTO;
import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
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
		this.createItemStateOnWorkflow(TestConstants.IN_DEVELOPMENT, false);
		this.anItemInDevelopmentStateDTO = this.anItemStateDTO;
	}

	@Override
	public void tearDown() throws Exception {
		this.removeTransition(this.anItemPendingStateDTO, aTransitionDTO);
		this.removeItemStateFromWorkflow(anItemInDevelopmentStateDTO);
		this.removeItemStateFromWorkflow(anItemPendingStateDTO);
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
	}

	public void testCreateTransition() {
		aTransitionDTO = this.createTransition(this.anItemPendingStateDTO, this.anItemInDevelopmentStateDTO,
				TestConstants.TRANSITION_IN_DEVELOPMENT, TestConstants.TRANSITION_IN_DEVELOPMENT);
		this.refreshWorkflow();
	}
}
