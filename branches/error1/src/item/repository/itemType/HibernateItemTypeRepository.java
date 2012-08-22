package item.repository.itemType;

import item.domain.itemType.ItemType;
import item.dto.itemType.ItemTypeDTO;
import item.dto.itemType.ItemTypeDTOForLists;
import item.exception.itemType.UnknownItemTypeException;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Query;

import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

/**
 * @author Ignacio Gallego (ignaciogallego@gmail.com)
 */
public class HibernateItemTypeRepository extends HibernateBaseRepository implements ItemTypeRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return ItemType.class;
	}

	@Override
	public ItemType getItemTypeByOid(String anOid) throws UnknownItemTypeException {
		ItemType anItemType = (ItemType)this.findeByOid(this.getEntityClass(), anOid);
		if (anItemType == null) {
			throw new UnknownItemTypeException();
		}
		return anItemType;
	}
	
	@Override
	public ItemType getItemTypeByName(String itemTypeName) throws UnknownItemTypeException {
		ItemType item = null;
		try {
			item = (ItemType) this.getEntityByUniqueField("getItemTypeByName", "itemTypeName", itemTypeName);
		} catch (BaseException aBaseException) {
			// TODO ver que hacer!!
		}
		if (item == null) {
			throw new UnknownItemTypeException("El item " + itemTypeName + " no existe.");
		}
		return item;

	}

	@Override
	public ItemType getItemTypeByDTO(ItemTypeDTO aItemTypeDTO) throws UnknownItemTypeException {
		return this.getItemTypeByOid(aItemTypeDTO.getOid());
	}

	@Override
	public Collection<ItemType> getItemTypesByDTOsList(Collection<ItemTypeDTOForLists> itemTypeDTOs)
			throws UnknownItemTypeException {
		Collection<String> oids = this.getOidsFromColleccionOfDTOs(itemTypeDTOs);

		Query aQuery = this.getNamedQuery("getItemTypesByOids");

		aQuery.setParameterList("oids", oids);

		@SuppressWarnings("unchecked")
		Collection<ItemType> itemTypes = aQuery.list();

		if (itemTypes == null) {
			throw new UnknownItemTypeException("No se encontro ningun item de la lista.");
		}
		if (itemTypes.size() != itemTypeDTOs.size()) {
			throw new UnknownItemTypeException("Alguno de los tipos de items de la lista no se encontró o fue eliminado.");
		}
		return new HashSet<ItemType>(itemTypes);
	}
}
