package base.dto;

import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractDTOForLists extends AbstractDTO {

	public AbstractDTOForLists(BaseDomain aBaseDomainObject) {
		super(aBaseDomainObject);
	}

	public AbstractDTOForLists(AbstractDTO anAbstractDTO) {
		super(anAbstractDTO);
	}

}
