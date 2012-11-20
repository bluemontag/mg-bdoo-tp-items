package workflow.test.state;

import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateCreationTest extends ItemStateServiceTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.createWorkflow();
	}

	public void testItemStateCreation() {

		this.createItemStateOnWorkflow(TestConstants.PENDING, true);
		this.refreshWorkflowDTO();
		assertEquals(this.aWorkflowDTO.getInitialState().getOid(), this.anItemStateDTO.getOid());
		assertEquals(TestConstants.PENDING, this.anItemStateDTO.getName());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeItemStateFromWorkflow(this.anItemStateDTO);
		this.refreshWorkflowDTO();
		this.removeWorkflow();
		super.tearDown();
	}
}
