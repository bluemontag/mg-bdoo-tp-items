/**
 * 
 */
package item.service;

import item.domain.Item;
import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;
import item.exception.itemType.UnknownItemTypeException;
import itemTracker.domain.ItemTracker;

import java.util.Collection;

import workflow.domain.state.domain.ItemState;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public class ItemServiceImpl extends AbstractServiceImpl implements
		ItemServiceBI {

	/* ______________________________________________________________________________________
	 * 
	 * Creating 
	 * ______________________________________________________________________________________
	 */
	@Override
	public ItemDTO createItem(String sessionToken, Long itemNum, String description, Integer priority, ItemType type)
			throws ItemAlreadyExistsException {

		try {//first verify if it exists in the repository
			this.getItemRepository().getItemByNum(itemNum);
		} catch (UnknownItemException unknownItemException) {
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			
			//responsibility of creating an item is in services layer.
			Item item = new Item(itemNum, description, priority, type);
			
			//set initial state:
			item.setCurrentState(type.getWorkflow().getInitialState());
			item.setResponsible(type.getInitialTeam().getUsers().iterator().next()); //pongo como responsable al primer usuario del team
			//add to root object
			theItemTracker.addItem(item);

			ItemDTO itemDTO = (ItemDTO) ItemDTOFactory.getInstance().getDTO(item);
			return itemDTO;
		}
		throw new ItemAlreadyExistsException("El item " + itemNum + " ya existe.");		
		
	}

	/* ______________________________________________________________________________________
	 * 
	 * Listing 
	 * ______________________________________________________________________________________
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

	/* ______________________________________________________________________________________
	 * 
	 * Retrieving 
	 * ______________________________________________________________________________________
	 */
	@Override
	public ItemDTO getItemByNum(String sessionToken, Long itemNum)
			throws UnknownItemException {
		Item item = this.getItemRepository().getItemByNum(itemNum);
		
		ItemDTO dto = (ItemDTO)ItemDTOFactory.getInstance().getDTO(item);
		
		return dto;
	}

	@Override
	public ItemDTO getItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {
		Item item = this.getItemRepository().getItemByNum(itemDTO.getItemNum());
		
		ItemDTO dto = (ItemDTO)ItemDTOFactory.getInstance().getDTO(item);
		
		return dto;
	}

	/* ______________________________________________________________________________________
	 * 
	 * Updating 
	 * ______________________________________________________________________________________
	 */
	@Override
	public void updateItem(String sessionToken, ItemDTO itemToUpdateDTO)
			throws UnknownItemException, DTOConcurrencyException {

		Item itemToUpdate = this.getItemRepository().getItemByOid(itemToUpdateDTO.getOid());
		this.checkDTOConcurrency(itemToUpdateDTO, itemToUpdate);
		
		//cambio propiedades simples (tipos primitivos)
		itemToUpdate.setDescription(itemToUpdateDTO.getDescription());
		itemToUpdate.setItemNum(itemToUpdateDTO.getItemNum());
		itemToUpdate.setPriority(itemToUpdateDTO.getPriority());
		
		//intento actualizar el tipo
		try {
			ItemType newType = this.getItemTypeRepository().getItemTypeByName(itemToUpdateDTO.getType());
			itemToUpdate.setType(newType);
		} catch (UnknownItemTypeException e) {
			System.out.println("No se pudo cambiar el tipo del item "+itemToUpdate.getItemNum()+".");
		} catch (Exception e) {
			System.out.println("No se pudo cambiar el tipo del item "+itemToUpdate.getItemNum()+".  EXCEPCION DESCONOCIDA.");
		}
	}
	
	public ItemStateDTO executeTransition(String sessionToken, Long itemNum, String transition) throws BadTransitionException, UnknownItemException {
		//first, we get the item
		Item i = this.getItemRepository().getItemByNum(itemNum);
		ItemState newState = i.executeTransition(transition);
		
		ItemStateDTO newStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(newState);
		return newStateDTO;
	}

	/* ______________________________________________________________________________________
	 * 
	 * Removing 
	 * ______________________________________________________________________________________
	 */
	@Override
	public void logicalRemoveItemByName(String sessionToken, String itemName)
			throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalRemoveItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	@Deprecated
	@Override
	public void removeItem(String sessionToken, ItemDTO itemDTO)
			throws UnknownItemException {

		Item item = this.getItemRepository().getItemByNum(itemDTO.getItemNum());
		
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeItem(item);
	}

}
