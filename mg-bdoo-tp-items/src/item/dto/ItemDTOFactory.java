/**
 * 
 */
package item.dto;

import item.domain.Item;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */

public class ItemDTOFactory extends DTOFactory {

	public static DTOFactory getInstance() {
		return new ItemDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain anItem) {
		return new ItemDTO((Item) anItem);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new ItemDTOForLists((Item) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anItemDTO) {
		return new ItemDTOForLists((ItemDTO) anItemDTO);
	}
}
