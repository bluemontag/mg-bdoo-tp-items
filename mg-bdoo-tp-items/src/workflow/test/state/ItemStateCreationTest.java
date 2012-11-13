/**
 * @author igallego 
 */
package workflow.test.state;

import workflow.dto.state.ItemStateDTO;
import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateCreationTest extends ItemStateServiceTest {

	protected ItemStateDTO initialState;

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testItemStateCreation() {

		ItemStateDTO pendingState = this.createOrGetItemState(TestConstants.PENDING);
		ItemStateDTO inDevelopmentState = this.createOrGetItemState(TestConstants.IN_DEVELOPMENT);
		ItemStateDTO finalState = this.createOrGetItemState(TestConstants.FINAL);

		initialState = pendingState;

		this.addNextState(pendingState, inDevelopmentState);
		this.addNextState(inDevelopmentState, finalState);
		this.addNextState(finalState, pendingState);
	}

	@Override
	protected void tearDown() throws Exception {
		try {
			initialState = this.getItemStateService().getItemStateByDTO(this.sessionToken, initialState);
			this.getItemStateService().removeItemState(this.sessionToken, initialState);
		} catch (Exception e) {
			System.out.println("No se pudo eliminar el estado " + initialState.getName());
		}

		super.tearDown();
	}

}
