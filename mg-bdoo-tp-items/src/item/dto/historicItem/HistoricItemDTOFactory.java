/**
 * 
 */
package item.dto.historicItem;

import item.domain.historicItem.HistoricItem;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */

public class HistoricItemDTOFactory extends DTOFactory {

	private HistoricItemDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new HistoricItemDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aHistoricItem) {
		return new HistoricItemDTO((HistoricItem) aHistoricItem);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new HistoricItemDTOForLists((HistoricItem) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anItemDTO) {
		return new HistoricItemDTOForLists((HistoricItemDTO) anItemDTO);
	}
}
