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

	// Creation
	@Override
	public ItemTypeDTO createItemType(String sessionToken, String aTypeName, TeamDTO initialTeamDTO, WorkflowDTO wDTO)
			throws ItemTypeAlreadyExistsException, UnknownTeamException, UnknownWorkflowException,
			DTOConcurrencyException {
		try {// first verify if it exists in the repository
			this.getItemTypeRepository().getItemTypeByName(aTypeName);
		} catch (UnknownItemTypeException unknownItemTypeException) {
			// tomo los objetos para crear el ItemType
			Team aInitialTeam = this.getTeamRepository().getTeamByDTO(initialTeamDTO);
			Workflow aWorkFlow = this.getWorkflowRepository().getWorkflowByDTO(wDTO);

			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();

			// responsibility of creating an item type is in services layer.
			ItemType itemType = new ItemType(aTypeName, aWorkFlow, aInitialTeam);

			theItemTracker.addItemType(itemType);

			ItemTypeDTO itemTypeDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(itemType);
			return itemTypeDTO;
		}
		throw new ItemTypeAlreadyExistsException("El tipo de item " + aTypeName + " ya existe.");

	}

	// Listing
	@Override
	public Collection<ItemTypeDTO> listItemTypes(String sessionToken) {
		return null;
	}

	// Retrieving
	@Override
	public ItemTypeDTO getItemTypeByName(String sessionToken, String typeName) throws UnknownItemTypeException {
		ItemType it = this.getItemTypeRepository().getItemTypeByName(typeName);
		ItemTypeDTO itDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(it);
		return itDTO;
	}

	@Override
	public ItemTypeDTO getItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {
		ItemType it = this.getItemTypeRepository().getItemTypeByOid(itemTypeDTO.getOid());
		ItemTypeDTO itDTO = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(it);
		return itDTO;
	}

	// Updating
	@Override
	public void updateItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException,
			DTOConcurrencyException {

	}

	// Removing
	@Override
	public void logicalRemoveItemTypeByName(String sessionToken, String itemTypeName) throws UnknownItemTypeException {

	}

	@Override
	public void logicalRemoveItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException {

	}

	@Override
	public void removeItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException,
			DTOConcurrencyException {
		ItemType it = this.getItemTypeRepository().getItemTypeByOid(itemTypeDTO.getOid());
		this.checkDTOConcurrency(itemTypeDTO, it);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeItemType(it);
	}
}
