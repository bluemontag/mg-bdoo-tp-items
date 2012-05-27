package base.dto;

import java.util.Collection;
import java.util.HashSet;

import base.domain.BaseDomain;

public abstract class DTOFactory {

	public Collection<? extends AbstractDTOForLists> getDTOList(Collection<? extends BaseDomain> aBaseDomainList) {
		Collection<AbstractDTOForLists> aAbstractDTOForListsCollection = new HashSet<AbstractDTOForLists>();
		for(BaseDomain aBaseDomainObject: aBaseDomainList){
			AbstractDTOForLists aAbstractDTOForLists = this.getDTOForLists(aBaseDomainObject);
			aAbstractDTOForListsCollection.add(aAbstractDTOForLists);
		}
		return aAbstractDTOForListsCollection;
	}
	
	public abstract AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject);

	public abstract AbstractDTO getDTO(BaseDomain aBaseDomainObject);

}
