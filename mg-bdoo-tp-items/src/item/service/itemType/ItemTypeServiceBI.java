/**
 * 
 */
package item.service.itemType;

import item.dto.itemType.ItemTypeDTO;
import item.exception.itemType.ItemTypeAlreadyExistsException;
import item.exception.itemType.UnknownItemTypeException;

import java.util.Collection;

import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;
import base.exception.DTOConcurrencyException;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public interface ItemTypeServiceBI {
	//Creation
	public ItemTypeDTO createItemType(String sessionToken, String typeName, TeamDTO initialTeamDTO, WorkflowDTO wDTO) 
			throws ItemTypeAlreadyExistsException, UnknownTeamException, UnknownWorkflowException; 
	
	//Listing
	public Collection<ItemTypeDTO> listItemTypes(String sessionToken);
	
	//Retrieving
	public ItemTypeDTO getItemTypeByName(String sessionToken, String itemTypeName) throws UnknownItemTypeException;
	public ItemTypeDTO getItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException;
	
	//Updating
	public void updateItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException, DTOConcurrencyException;
	
	//Removing
	public void logicalRemoveItemTypeByName(String sessionToken, String itemTypeName) throws UnknownItemTypeException;
	public void logicalRemoveItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException;
	public void removeItemType(String sessionToken, ItemTypeDTO itemTypeDTO) throws UnknownItemTypeException, DTOConcurrencyException;
}
