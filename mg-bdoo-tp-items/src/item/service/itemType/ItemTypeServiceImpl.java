/**
 * 
 */
package item.service.itemType;

import item.domain.itemType.ItemType;
import item.dto.itemType.ItemTypeDTO;
import item.dto.itemType.ItemTypeDTOFactory;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import item.exception.itemType.UnknownItemTypeException;
import itemTracker.domain.ItemTracker;

import java.util.Collection;

import user.domain.team.Team;
import workflow.domain.Workflow;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author ignacio
 *
 */
public class ItemTypeServiceImpl extends AbstractServiceImpl implements ItemTypeServiceBI {

	/* ________________________________________________________
	 * 
	 * Creation
	 * ________________________________________________________
	 */
	public ItemTypeDTO createItemType(String sessionToken, String typeName, Team initialTeam, Workflow w) 
				throws ItemTypeAlreadyExistsException {
		try {//first verify if it exists in the repository
			this.getItemTypeRepository().getItemTypeByName(typeName);
		} catch (UnknownItemTypeException unknownItemTypeException) {
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			
			//responsibility of creating an item type is in services layer.
			ItemType itemType = new ItemType(typeName, w, initialTeam);
			
			theItemTracker.addItemType(itemType);

			ItemTypeDTO itemTypeDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(itemType);
			return itemTypeDTO;
		}
		throw new ItemTypeAlreadyExistsException("El tipo de item " + typeName + " ya existe.");		

	}
		
	/* ________________________________________________________
	 * 
	 * Listing
	 * ________________________________________________________
	 */
	public Collection<ItemTypeDTO> listItemTypes(String sessionToken) {
		return null;
	}
		
	/* ________________________________________________________
	 * 
	 * Retrieving
	 * ________________________________________________________
	 */
	public ItemTypeDTO getItemTypeByName(String sessionToken, String itemTypeName) throws UnknownItemTypeException {
		return null;
	}
	public ItemTypeDTO getItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {
		return null;
	}
		
	/* ________________________________________________________
	 * 
	 * Updating
	 * ________________________________________________________
	 */
	public void updateItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException, DTOConcurrencyException {

	}
		
	/* ________________________________________________________
	 * 
	 * Removing
	 * ________________________________________________________
	 */
	public void logicalRemoveItemTypeByName(String sessionToken, String itemTypeName) throws UnknownItemTypeException {
		
	}
	public void logicalRemoveItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {
		
	}
	public void removeItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {
		
	}
}
