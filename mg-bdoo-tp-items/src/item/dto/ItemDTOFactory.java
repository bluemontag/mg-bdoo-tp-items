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
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public class ItemDTOFactory extends DTOFactory {

	
	public static DTOFactory getInstance() {
		return new ItemDTOFactory();
	}
	
	@Override
	public AbstractDTO getDTO(BaseDomain anItem) {
		return new ItemDTO((Item) anItem);
	}

	/* (non-Javadoc)
	 * @see base.dto.DTOFactory#getDTOForListInstance(base.dto.AbstractDTO)
	 */
	@Override
	protected AbstractDTOForLists getDTOForListInstance(
			AbstractDTO anAbstractDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see base.dto.DTOFactory#getDTOForLists(base.domain.BaseDomain)
	 */
	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
