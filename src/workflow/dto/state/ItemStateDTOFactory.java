package workflow.dto.state;

import workflow.domain.state.domain.ItemState;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Ignacio Gallego (ignaciogallego@gmail.com)
 */
public class ItemStateDTOFactory extends DTOFactory {

	private ItemStateDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new ItemStateDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aState) {
		return new ItemStateDTO((ItemState) aState);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new ItemStateDTOForLists((ItemState) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anStateDTO) {
		return new ItemStateDTOForLists((ItemStateDTO) anStateDTO);
	}

}
