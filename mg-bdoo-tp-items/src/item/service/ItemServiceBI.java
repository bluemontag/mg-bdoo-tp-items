/**
 * 
 */
package item.service;

import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;

import java.util.Collection;

import workflow.dto.state.ItemStateDTO;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public interface ItemServiceBI {
	//Creation
	public ItemDTO createItem(String sessionToken, Long itemNum, String description, Integer priority, ItemType type) 
			throws ItemAlreadyExistsException; 
	
	//Listing
	public Collection<ItemDTO> listItems(String sessionToken);
	
	//Retrieving
	public ItemDTO getItemByName(String sessionToken, String itemName) throws UnknownItemException;
	public ItemDTO getItemByNum(String sessionToken, Long itemNum) throws UnknownItemException;
	public ItemDTO getItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException;
	
	//Updating
	public void updateItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException, DTOConcurrencyException;
	public ItemStateDTO executeTransition(String sessionToken, Long itemNum, String transition) throws BadTransitionException, UnknownItemException;
	
	//Removing
	public void logicalRemoveItemByName(String sessionToken, String itemName) throws UnknownItemException;
	public void logicalRemoveItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException;
	public void removeItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException;
}
