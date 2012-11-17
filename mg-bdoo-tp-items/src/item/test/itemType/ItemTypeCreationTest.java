/**
 * @author igallego 
 */
package item.test.itemType;

import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class ItemTypeCreationTest extends ItemTypeServiceTest {

	@Override
	public void setUp() throws Exception {
		super.setUp(); // crea los servicios y loguea usuario
		this.createAUserCollection();
		this.createTeam();
		this.createWorkflow();
	}

	public void testItemTypeCreation() throws Exception {
		this.createItemType();
		assertEquals(TestConstants.ITEM_TYPE_NAME, this.anItemTypeDTO.getName());
	}

	@Override
	protected void tearDown() throws Exception {
		this.removeItemType();
		this.removeWorkflow();
		this.removeTeam();
		this.removeTheUserCollection();
		super.tearDown();
	}

}
