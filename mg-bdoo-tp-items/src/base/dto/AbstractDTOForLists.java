package base.dto;

import base.domain.BaseDomain;

public abstract class AbstractDTOForLists extends AbstractDTO {

	public AbstractDTOForLists(BaseDomain aBaseDomainObject) {
		super(aBaseDomainObject);
	}

	public AbstractDTOForLists(AbstractDTO anAbstractDTO) {
		super(anAbstractDTO);
	}

}
