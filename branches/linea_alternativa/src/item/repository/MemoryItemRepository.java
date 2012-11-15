package item.repository;

import item.domain.Item;
import item.dto.ItemDTO;
import item.dto.ItemDTOForLists;
import item.exception.UnknownItemException;

import java.util.Collection;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class MemoryItemRepository implements ItemRepositoryBI {

	@Override
	public Item getItemByNum(Long itemNum) throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByOid(String oid) throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByDTO(ItemDTO aItemDTO) throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Item> getItemsByDTOsList(Collection<ItemDTOForLists> itemsDTOs) throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
