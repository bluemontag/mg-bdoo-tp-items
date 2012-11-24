/**
 * @author igallego 
 */
package item.test.itemType;

import item.dto.itemType.ItemTypeDTO;
import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class ItemTypeCreationTest extends ItemTypeServiceTest {

	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private TeamDTO aTeamDTO;
	private String testCode;

	@Override
	public void setUp() throws Exception {
		super.setUp(); // crea los servicios y loguea usuario
		testCode = " " + ItemTypeCreationTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
	}

	public void testItemTypeCreation() throws Exception {
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);
		assertEquals(TestConstants.ITEM_TYPE_NAME + testCode, this.anItemTypeDTO.getName());
		assertEquals(this.aTeamDTO.getName(), this.anItemTypeDTO.getInitialTeamDTO().getName());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
		super.tearDown();
	}

}
