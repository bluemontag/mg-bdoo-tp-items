/**
 * @author igallego 
 */
package workflow.test;

import workflow.dto.WorkflowDTO;
import base.test.BaseTestCase;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class WorkflowCreationTest extends BaseTestCase {

	private WorkflowDTO aWorkflowDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	public void testWorkflowCreation() {
		testCode = " " + WorkflowCreationTest.class.getSimpleName();
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		assertEquals(TestConstants.WORKFLOW_NAME + testCode, this.aWorkflowDTO.getName());
		assertTrue(aWorkflowDTO.getItemStates().isEmpty());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeWorkflow(this.aWorkflowDTO);
		super.tearDown();
	}
}
