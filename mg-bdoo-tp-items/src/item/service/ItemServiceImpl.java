package item.service;

import item.domain.Item;
import item.domain.historicItem.HistoricItem;
import item.domain.itemType.ItemType;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;
import item.dto.historicItem.HistoricItemDTOFactory;
import item.dto.historicItem.HistoricItemDTOForLists;
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
import base.exception.UserNotLoggedException;
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
			throws ItemAlreadyExistsException, UnknownItemTypeException, UserNotLoggedException {

		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();

		ItemType itemType = this.getItemTypeRepository().getItemTypeByDTO(typeDTO);
		// pongo como responsable al primer usuario del team.
		User firstUser = itemType.getInitialTeam().getUsers().iterator().next();
		ItemState initialState = itemType.getWorkflow().getInitialState();
		long itemNum = theItemTracker.getNextItemNum();

		Item item = new Item(itemNum, description, priority, itemType, firstUser, initialState);
		User anUser = this.getCurrentUser(sessionToken);
		item.saveItem(anUser);
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
	@SuppressWarnings("unchecked")
	public Collection<? extends HistoricItemDTOForLists> listHistoricItems(String sessionToken, ItemDTO anItemDTO)
			throws UnknownItemException {
		Item item = this.getItemRepository().getItemByDTO(anItemDTO);
		Collection<HistoricItem> history = item.getHistory();
		Collection<HistoricItemDTOForLists> historyDTO = (Collection<HistoricItemDTOForLists>) HistoricItemDTOFactory
				.getInstance().getDTOList(history);
		return historyDTO;
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
			DTOConcurrencyException, UnknownItemTypeException {

		Item itemToUpdate = this.getItemRepository().getItemByOid(itemToUpdateDTO.getOid());
		this.checkDTOConcurrency(itemToUpdateDTO, itemToUpdate);

		// cambio propiedades simples (tipos primitivos)
		itemToUpdate.setDescription(itemToUpdateDTO.getDescription());
		itemToUpdate.setItemNum(itemToUpdateDTO.getItemNum());
		itemToUpdate.setPriority(itemToUpdateDTO.getPriority());

		ItemType newType = this.getItemTypeRepository().getItemTypeByName(itemToUpdateDTO.getType().getName());
		itemToUpdate.setType(newType);
	}

	@Override
	public void executeTransition(String sessionToken, ItemDTO anItemDTO, String transitionCode)
			throws BadTransitionException, UnknownItemException, DTOConcurrencyException, UserNotLoggedException {

		Item anItem = this.getItemRepository().getItemByDTO(anItemDTO);
		this.checkDTOConcurrency(anItemDTO, anItem);
		anItem.executeTransition(transitionCode);
		User anUser = this.getCurrentUser(sessionToken);
		anItem.saveItem(anUser);
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
