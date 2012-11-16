/**
 * 
 */
package item.dto;

import item.domain.Item;
import base.dto.AbstractDTOForLists;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */

public class ItemDTOForLists extends AbstractDTOForLists {

	public ItemDTOForLists(ItemDTO anItemDTO) {
		super(anItemDTO);
	}

	public ItemDTOForLists(Item aBaseDomainObject) {
		super(aBaseDomainObject);
	}
}
