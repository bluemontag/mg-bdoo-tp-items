package item.test;

import org.junit.After;
import org.junit.Before;

import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemCreateTest extends ItemServiceTest {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
		this.createTeam();
		this.createWorkflow();
		this.createItemType();

	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.removeItem();
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
	}

	public void testCreateItem() {
		this.createItem();
		assertNotNull(this.anItemDTO.getItemNum());
		assertEquals(this.anItemDTO.getDescription(), TestConstants.ITEM_DESCRIPTION);
		assertEquals(this.anItemDTO.getPriority(), TestConstants.PRIORITY);
		assertEquals(this.anItemDTO.getType().getOid(), this.anItemTypeDTO.getOid());
	}
}