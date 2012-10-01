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
import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;
import workflow.domain.Workflow;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;
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
	public ItemTypeDTO createItemType(String sessionToken, String typeName, TeamDTO initialTeamDTO, WorkflowDTO wDTO) 
				throws ItemTypeAlreadyExistsException, UnknownTeamException, UnknownWorkflowException {
		try {//first verify if it exists in the repository
			this.getItemTypeRepository().getItemTypeByName(typeName);
		} catch (UnknownItemTypeException unknownItemTypeException) {
			//tomo los objetos para crear el ItemType
			Team initialTeam = this.getTeamRepository().getTeamByDTO(initialTeamDTO);
			Workflow w = this.getWorkflowRepository().getWorkflowByDTO(wDTO);
			
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
	public ItemTypeDTO getItemTypeByName(String sessionToken, String typeName) throws UnknownItemTypeException {
		ItemType it = this.getItemTypeRepository().getItemTypeByName(typeName);
		ItemTypeDTO itDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(it);
		return itDTO;
	}
	
	public ItemTypeDTO getItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {
		ItemType it = this.getItemTypeRepository().getItemTypeByOid(itemTypeDTO.getOid());
		ItemTypeDTO itDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(it);
		return itDTO;
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
	public void removeItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException, DTOConcurrencyException {
		ItemType it = this.getItemTypeRepository().getItemTypeByOid(itemTypeDTO.getOid());
		this.checkDTOConcurrency(itemTypeDTO, it);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeItemType(it);
	}
}
