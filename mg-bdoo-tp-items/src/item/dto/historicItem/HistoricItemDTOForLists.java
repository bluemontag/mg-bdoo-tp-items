/**
 * 
 */
package item.dto.historicItem;

import item.domain.historicItem.HistoricItem;
import base.dto.AbstractDTOForLists;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */

public class HistoricItemDTOForLists extends AbstractDTOForLists {

	public HistoricItemDTOForLists(HistoricItemDTO anItemDTO) {
		super(anItemDTO);
	}

	public HistoricItemDTOForLists(HistoricItem aBaseDomainObject) {
		super(aBaseDomainObject);
	}
}
