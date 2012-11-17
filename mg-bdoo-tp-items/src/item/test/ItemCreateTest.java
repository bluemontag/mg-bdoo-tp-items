package item.test;

import item.dto.ItemDTO;

import org.junit.After;
import org.junit.Before;

import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemCreateTest extends ItemServiceTest {

	protected ItemDTO anItemDTO;

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
		this.deleteItem();
		this.removeWorkflow();
		this.removeTeam();
		this.deleteTheUserCollection();
	}

	// protected void deleteWorkflow() {
	// try {
	// this.workflowService.removeWorkflow(sessionToken, this.aWorkflowDTO);
	// } catch (UnknownWorkflowException e) {
	// fail("El workflow que de");
	// } catch (DTOConcurrencyException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void testCreateItem() {
		this.createItem();
		assertEquals(this.anItemDTO.getDescription(), TestConstants.ITEM_DESCRIPTION);
		assertEquals(this.anItemDTO.getPriority(), TestConstants.PRIORITY);
		assertEquals(this.anItemDTO.getType().getOid(), this.anItemTypeDTO.getOid());
	}
}