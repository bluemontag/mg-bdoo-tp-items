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

	@Override
	public void setUp() throws Exception {
		super.setUp(); // crea los servicios y loguea usuario
		this.aUserDTOForListCollection = this.createAUserCollection(ItemTypeCreationTest.class.toString());
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.NEW_TEAM_NAME);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME);
	}

	public void testItemTypeCreation() throws Exception {
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME, this.aTeamDTO, this.aWorkflowDTO);
		assertEquals(TestConstants.ITEM_TYPE_NAME, this.anItemTypeDTO.getName());
		assertEquals(this.aTeamDTO.getName(), this.anItemTypeDTO.getInitialTeamDTO().getName());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(ItemTypeCreationTest.class.toString());
		super.tearDown();
	}

}
