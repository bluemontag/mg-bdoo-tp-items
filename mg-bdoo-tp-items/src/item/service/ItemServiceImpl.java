/**
 * 
 */
package item.service;

import item.domain.Item;
import item.domain.ItemType;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;
import itemTracker.domain.ItemTracker;

import java.util.Collection;

import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public class ItemServiceImpl extends AbstractServiceImpl implements
		ItemServiceBI {

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#createItem(java.lang.String, java.lang.String)
	 */
	@Override
	public ItemDTO createItem(String sessionToken, Long itemNum, String description, Integer priority, ItemType type)
			throws ItemAlreadyExistsException {

		try {//first verify if it exists in the repository
			this.getItemRepository().getItemByNum(itemNum);
		} catch (UnknownItemException unknownItemException) {
			ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
			
			//responsibility of creating an item is in services layer.
			Item item = new Item(itemNum, description, priority, type);
			
			theItemTracker.addItem(item);

			ItemDTO itemDTO = (ItemDTO) ItemDTOFactory.getInstance().getDTO(item);
			return itemDTO;
		}
		throw new ItemAlreadyExistsException("El item " + itemNum + " ya existe.");		
		
	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#listItems(java.lang.String)
	 */
	@Override
	public Collection<ItemDTO> listItems(String sessionToken) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#getItemByName(java.lang.String, java.lang.String)
	 */
	@Override
	public ItemDTO getItemByName(String sessionToken, String itemName)
			throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#getItemByItemNum(java.lang.String, java.lang.Long)
	 */
	@Override
	public ItemDTO getItemByItemNum(String sessionToken, Long itemNum)
			throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#getItem(java.lang.String, item.dto.ItemDTO)
	 */
	@Override
	public ItemDTO getItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#updateItem(java.lang.String, item.dto.ItemDTO)
	 */
	@Override
	public void updateItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException, DTOConcurrencyException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#logicalRemoveItemByName(java.lang.String, java.lang.String)
	 */
	@Override
	public void logicalRemoveItemByName(String sessionToken, String itemName)
			throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#logicalRemoveItem(java.lang.String, item.dto.ItemDTO)
	 */
	@Override
	public void logicalRemoveItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see item.service.ItemServiceBI#removeItem(java.lang.String, item.dto.ItemDTO)
	 */
	@Override
	public void removeItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {
		// TODO Auto-generated method stub

	}

}
