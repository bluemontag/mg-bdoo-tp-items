package base.dto;

import java.util.ArrayList;
import java.util.Collection;

import base.domain.BaseDomain;

public abstract class DTOFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<? extends AbstractDTOForLists> getDTOsForListFromDTOs(
			Collection<? extends AbstractDTO> abstractsDTO) {
		Collection dtosForLists = new ArrayList();
		for (AbstractDTO anAbstractDTO : abstractsDTO) {
			dtosForLists.add(this.getDTOForListInstance(anAbstractDTO));
		}
		return dtosForLists;
	}

	protected abstract AbstractDTOForLists getDTOForListInstance(AbstractDTO anAbstractDTO);

	public Collection<? extends AbstractDTOForLists> getDTOList(Collection<? extends BaseDomain> aBaseDomainList) {
		Collection<AbstractDTOForLists> aAbstractDTOForListsCollection = new ArrayList<AbstractDTOForLists>();
		for (BaseDomain aBaseDomainObject : aBaseDomainList) {
			AbstractDTOForLists aAbstractDTOForLists = this.getDTOForLists(aBaseDomainObject);
			aAbstractDTOForListsCollection.add(aAbstractDTOForLists);
		}
		return aAbstractDTOForListsCollection;
	}

	public abstract AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject);

	public abstract AbstractDTO getDTO(BaseDomain aBaseDomainObject);

}
