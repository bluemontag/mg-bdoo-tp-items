/**
 * @author igallego 
 */
package workflow.test.transition;

import workflow.dto.state.ItemStateDTO;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class TransitionCreationTest extends TransitionServiceTest {

	protected ItemStateDTO initialStateDTO;
	protected ItemStateDTO anItemPendingStateDTO;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.createWorkflow();

	}

	public void testItemStateCreation() {

	}

	@Override
	protected void tearDown() throws Exception {

		super.tearDown();
	}
}
