package item.service;

import item.domain.Item;
import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;
import item.dto.itemType.ItemTypeDTO;
import item.exception.ItemAlreadyExistsException;
import item.exception.UnknownItemException;
import item.exception.itemType.UnknownItemTypeException;
import itemTracker.domain.ItemTracker;

import java.util.Collection;

import user.domain.User;
import workflow.domain.state.ItemState;
import workflow.exception.transition.BadTransitionException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         15/06/2012
 */
public class ItemServiceImpl extends AbstractServiceImpl implements ItemServiceBI {

	// Creation
	@Override
	public ItemDTO createItem(String sessionToken, String description, Integer priority, ItemTypeDTO typeDTO)
			throws ItemAlreadyExistsException, UnknownItemTypeException {

		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();

		ItemType itemType = this.getItemTypeRepository().getItemTypeByDTO(typeDTO);
		// pongo como responsable al primer usuario del team.
		User firstUser = itemType.getInitialTeam().getUsers().iterator().next();
		ItemState initialState = itemType.getWorkflow().getInitialState();

		Item item = new Item(description, priority, itemType, firstUser, initialState);

		theItemTracker.addItem(item);

		ItemDTO itemDTO = (ItemDTO) ItemDTOFactory.getInstance().getDTO(item);
		return itemDTO;

	}

	// Listing
	@Override
	public Collection<ItemDTO> listItems(String sessionToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDTO getItemByName(String sessionToken, String itemName) throws UnknownItemException {
		// TODO Auto-generated method stub
		return null;
	}

	// Retrieving
	@Override
	public ItemDTO getItemByNum(String sessionToken, Long itemNum) throws UnknownItemException {
		Item item = this.getItemRepository().getItemByNum(itemNum);

		ItemDTO dto = (ItemDTO) ItemDTOFactory.getInstance().getDTO(item);

		return dto;
	}

	@Override
	public ItemDTO getItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException {
		Item item = this.getItemRepository().getItemByNum(itemDTO.getItemNum());

		ItemDTO dto = (ItemDTO) ItemDTOFactory.getInstance().getDTO(item);

		return dto;
	}

	// Updating
	@Override
	public void updateItem(String sessionToken, ItemDTO itemToUpdateDTO) throws UnknownItemException,
			DTOConcurrencyException {

		Item itemToUpdate = this.getItemRepository().getItemByOid(itemToUpdateDTO.getOid());
		this.checkDTOConcurrency(itemToUpdateDTO, itemToUpdate);

		// cambio propiedades simples (tipos primitivos)
		itemToUpdate.setDescription(itemToUpdateDTO.getDescription());
		itemToUpdate.setItemNum(itemToUpdateDTO.getItemNum());
		itemToUpdate.setPriority(itemToUpdateDTO.getPriority());

		// intento actualizar el tipo
		try {
			ItemType newType = this.getItemTypeRepository().getItemTypeByName(itemToUpdateDTO.getType().getName());
			itemToUpdate.setType(newType);
		} catch (UnknownItemTypeException e) {
			System.out.println("No se pudo cambiar el tipo del item " + itemToUpdate.getItemNum() + ".");
		} catch (Exception e) {
			System.out.println("No se pudo cambiar el tipo del item " + itemToUpdate.getItemNum()
					+ ".  EXCEPCION DESCONOCIDA.");
		}
	}

	@Override
	public void executeTransition(String sessionToken, Long itemNum, String transition) throws BadTransitionException,
			UnknownItemException {

		Item anItem = this.getItemRepository().getItemByNum(itemNum);
		anItem.executeTransition(transition);
	}

	// Removing
	@Override
	public void logicalRemoveItemByName(String sessionToken, String itemName) throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalRemoveItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException {
		// TODO Auto-generated method stub

	}

	@Deprecated
	@Override
	public void removeItem(String sessionToken, ItemDTO itemDTO) throws UnknownItemException, DTOConcurrencyException {

		Item item = this.getItemRepository().getItemByNum(itemDTO.getItemNum());
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeItem(item);
	}

}
