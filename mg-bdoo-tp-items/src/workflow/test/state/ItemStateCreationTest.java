package workflow.test.state;

import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import base.test.TestConstants;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         03/07/2012
 */
public class ItemStateCreationTest extends ItemStateServiceTest {

	private WorkflowDTO aWorkflowDTO;
	private ItemStateDTO anItemStateDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ItemStateCreationTest.class.getSimpleName();
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
	}

	public void testItemStateCreation() {

		this.anItemStateDTO = this.createItemStateOnWorkflow(this.aWorkflowDTO, TestConstants.PENDING_STATE, true);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		assertEquals(this.aWorkflowDTO.getInitialState().getOid(), this.anItemStateDTO.getOid());
		assertEquals(TestConstants.PENDING_STATE, this.anItemStateDTO.getName());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeItemStateFromWorkflow(this.aWorkflowDTO, this.anItemStateDTO);
		this.aWorkflowDTO = this.getWorkflowDTO(this.aWorkflowDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		super.tearDown();
	}
}
