package base.dto;

import java.util.Collection;
import java.util.HashSet;

import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class DTOFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<? extends AbstractDTOForLists> getDTOsForListFromDTOs(
			Collection<? extends AbstractDTO> abstractsDTO) {
		Collection dtosForLists = new HashSet();
		for (AbstractDTO anAbstractDTO : abstractsDTO) {
			dtosForLists.add(this.getDTOForListInstance(anAbstractDTO));
		}
		return dtosForLists;
	}

	protected abstract AbstractDTOForLists getDTOForListInstance(AbstractDTO anAbstractDTO);

	public Collection<? extends AbstractDTOForLists> getDTOList(Collection<? extends BaseDomain> aBaseDomainList) {
		Collection<AbstractDTOForLists> aAbstractDTOForListsCollection = new HashSet<AbstractDTOForLists>();
		for (BaseDomain aBaseDomainObject : aBaseDomainList) {
			AbstractDTOForLists aAbstractDTOForLists = this.getDTOForLists(aBaseDomainObject);
			aAbstractDTOForListsCollection.add(aAbstractDTOForLists);
		}
		return aAbstractDTOForListsCollection;
	}

	public abstract AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject);

	public abstract AbstractDTO getDTO(BaseDomain aBaseDomainObject);

}
