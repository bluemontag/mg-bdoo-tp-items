package item.repository.itemType;

import item.domain.itemType.ItemType;
import item.dto.itemType.ItemTypeDTO;
import item.dto.itemType.ItemTypeDTOForLists;
import item.exception.itemType.UnknownItemTypeException;

import java.util.Collection;

/**
 * @author Ignacio Gallego (ignaciogallego@gmail.com)
 */
public class MemoryItemTypeRepository implements ItemTypeRepositoryBI {

	@Override
	public ItemType getItemTypeByName(String itemTypeName) throws UnknownItemTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemType getItemTypeByOid(String oid) throws UnknownItemTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemType getItemTypeByDTO(ItemTypeDTO aItemDTO) throws UnknownItemTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ItemType> getItemTypesByDTOsList(Collection<ItemTypeDTOForLists> itemsDTOs)
			throws UnknownItemTypeException {
		// TODO Auto-generated method stub
		return null;
	}


}
