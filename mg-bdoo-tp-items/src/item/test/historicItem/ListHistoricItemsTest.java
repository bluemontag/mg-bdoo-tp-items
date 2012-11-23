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

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.aUserDTOForListCollection = this.createAUserCollection(ListHistoricItemsTest.class.toString());
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.NEW_TEAM_NAME);
		this.aWorkflowDTO = this.createWorkflow(TestConstants.WORKFLOW_NAME);
		this.anItemTypeDTO = this.createItemType(TestConstants.ITEM_TYPE_NAME, this.aTeamDTO, this.aWorkflowDTO);
		this.anItemDTO = this.createItem(TestConstants.ITEM_DESCRIPTION, TestConstants.PRIORITY, this.anItemTypeDTO);

	}

	@Override
	public void tearDown() throws Exception {
		this.removeItem(this.anItemDTO);
		this.removeItemType(this.anItemTypeDTO);
		this.removeWorkflow(this.aWorkflowDTO);
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(ListHistoricItemsTest.class.toString());
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