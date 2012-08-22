/**
 * 
 */
package item.dto.itemType;

import item.domain.itemType.ItemType;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 20/06/2012
 */
public class ItemTypeDTOFactory extends DTOFactory {

	
	public static DTOFactory getInstance() {
		return new ItemTypeDTOFactory();
	}
	
	@Override
	public AbstractDTO getDTO(BaseDomain anItemType) {
		return new ItemTypeDTO((ItemType) anItemType);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(
			AbstractDTO anAbstractDTO) {
		return null;
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
