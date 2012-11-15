package item.repository.itemType;

import item.domain.itemType.ItemType;
import item.dto.itemType.ItemTypeDTO;
import item.dto.itemType.ItemTypeDTOForLists;
import item.exception.itemType.UnknownItemTypeException;

import java.util.Collection;

public interface ItemTypeRepositoryBI {
	
	public ItemType getItemTypeByName(String itemTypeName) throws UnknownItemTypeException;

	public ItemType getItemTypeByOid(String oid) throws UnknownItemTypeException;

	public ItemType getItemTypeByDTO(ItemTypeDTO aItemDTO) throws UnknownItemTypeException;

	public Collection<ItemType> getItemTypesByDTOsList(Collection<ItemTypeDTOForLists> itemsDTOs) throws UnknownItemTypeException;
}
