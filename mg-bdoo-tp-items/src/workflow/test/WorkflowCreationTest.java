/**
 * @author igallego 
 */
package workflow.test;

import base.test.BaseTestCase;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class WorkflowCreationTest extends BaseTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testWorkflowCreation() {
		this.createWorkflow(TestConstants.WORKFLOW_NAME);
		assertEquals(TestConstants.WORKFLOW_NAME, this.aWorkflowDTO.getName());
		assertTrue(aWorkflowDTO.getItemStates().isEmpty());
	}

	@Override
	protected void tearDown() throws Exception {
		this.eliminarWorkflow(this.aWorkflowDTO);
		super.tearDown();
	}
}
