package item.test.historicItem;

import item.dto.ItemDTO;
import item.dto.historicItem.HistoricItemDTOForLists;
import item.dto.itemType.ItemTypeDTO;
import item.exception.UnknownItemException;
import item.test.ItemServiceTest;

import java.util.Collection;

import org.junit.Before;

import user.dto.team.TeamDTO;
import workflow.dto.WorkflowDTO;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ListHistoricItemsTest extends ItemServiceTest {

	private TeamDTO aTeamDTO;
	private WorkflowDTO aWorkflowDTO;
	private ItemTypeDTO anItemTypeDTO;
	private ItemDTO anItemDTO;
	private String testCode;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ListHistoricItemsTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME + testCode);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME + testCode, this.aTeamDTO,
				this.aWorkflowDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION + testCode, TestConstants.PRIORITY,
				this.anItemTypeDTO);

	}

	@Override
	public void tearDown() throws Exception {
		this.removeItem(this.anItemDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testListHistoricItems() {
		Collection<? extends HistoricItemDTOForLists> historicItems = null;
		try {
			historicItems = this.itemService.listHistoricItems(sessionToken, this.anItemDTO);
		} catch (UnknownItemException e) {
			fail("El item numero " + this.anItemDTO.getItemNum() + " no existe.");
		}
		assertEquals(1, historicItems.size());
	}
}