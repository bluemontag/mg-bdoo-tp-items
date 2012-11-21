package item.test.historicItem;

import item.dto.historicItem.HistoricItemDTOForLists;
import item.exception.UnknownItemException;
import item.test.ItemServiceTest;

import java.util.Collection;

import org.junit.Before;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ListHistoricItemsTest extends ItemServiceTest {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
		this.createTeam();
		this.createWorkflow();
		this.createItemType();
		this.createItem();

	}

	@Override
	public void tearDown() throws Exception {
		this.removeItem();
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
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