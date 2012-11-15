package item.repository;

import item.domain.Item;
import item.dto.ItemDTO;
import item.dto.ItemDTOForLists;
import item.exception.UnknownItemException;

import java.util.Collection;

public interface ItemRepositoryBI {
	
	public Item getItemByNum(Long itemNum) throws UnknownItemException;

	public Item getItemByOid(String oid) throws UnknownItemException;

	public Item getItemByDTO(ItemDTO aItemDTO) throws UnknownItemException;

	public Collection<Item> getItemsByDTOsList(Collection<ItemDTOForLists> itemsDTOs) throws UnknownItemException;
}
