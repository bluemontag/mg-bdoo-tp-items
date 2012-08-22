/**
 * 
 */
package item.dto.itemType;

import item.domain.itemType.ItemType;
import base.dto.AbstractDTO;

/**
 * @author ignacio
 *
 * 20/06/2012
 */
public class ItemTypeDTO extends AbstractDTO {


	private String name;
	
	
	public ItemTypeDTO(ItemType anItemType) {
		super(anItemType);
		
		this.setName(anItemType.getTypeName());
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}