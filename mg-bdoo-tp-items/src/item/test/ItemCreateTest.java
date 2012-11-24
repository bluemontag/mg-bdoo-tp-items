package item.test;

import item.dto.ItemDTO;
import item.dto.itemType.ItemTypeDTO;

import org.junit.Before;

import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemCreateTest extends ItemServiceTest {

	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;
	private String testCode;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ItemCreateTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);

	}

	@Override
	public void tearDown() throws Exception {
		this.removeItem(this.anItemDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testCreateItem() {
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION + testCode, TestConstants.PRIORITY,
				this.anItemTypeDTO);
		assertNotNull(this.anItemDTO.getItemNum());
		assertEquals(this.anItemDTO.getDescription(), TestConstants.ITEM_DESCRIPTION + testCode);
		assertEquals(this.anItemDTO.getPriority(), TestConstants.PRIORITY);
		assertEquals(this.anItemDTO.getType().getOid(), this.anItemTypeDTO.getOid());
	}
}