package workflow.service.state;

import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;

/**
 * @author Ignacio Gallego
 */
public interface ItemStateServiceBI {

	// Creats

	public ItemStateDTO createItemState(String sessionToken, String itemStateName) throws ItemStateAlreadyExistsException;
	
	// Lists

	// Retrives
	public ItemStateDTO getItemStateByName(String sessionToken, String itemStateName) throws UnknownItemStateException;
	public ItemStateDTO getItemStateByOid(String sessionToken, String anOid) throws UnknownItemStateException;
	public ItemStateDTO getItemStateByDTO(String sessionToken, ItemStateDTO itemStateDTO) throws UnknownItemStateException;
	
	// Updates
	public void addNextState(String sessionToken, ItemStateDTO itemStateDTO, ItemStateDTO next) throws UnknownItemStateException,	DTOConcurrencyException;
	
	// Removes
	public void removeItemState(String sessionToken, ItemStateDTO itemStateDTO) throws UnknownItemStateException, DTOConcurrencyException;
	public void logicalRemoveItemState(String sessionToken, ItemStateDTO itemStateDTO) throws UnknownItemStateException;

	
	
}
