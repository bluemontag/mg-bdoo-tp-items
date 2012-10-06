/**
 * 
 */
package workflow.service.state;

import itemTracker.domain.ItemTracker;
import workflow.domain.state.ItemState;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import workflow.exception.state.ItemStateAlreadyExistsException;
import workflow.exception.state.UnknownItemStateException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Ignacio Gallego
 */
public class ItemStateServiceImpl extends AbstractServiceImpl implements ItemStateServiceBI {

	@Override
	public ItemStateDTO createItemState(String sessionToken, String itemStateName) throws ItemStateAlreadyExistsException {
		try {
			this.getItemStateRepository().getItemStateByName(itemStateName);
		} catch (UnknownItemStateException unknownItemStateException) {
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			
			ItemState itemState = new ItemState(itemStateName);
			
			theItemTracker.addItemState(itemState);

			ItemStateDTO wfDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
			return wfDTO;
		}
		throw new ItemStateAlreadyExistsException("El itemState " + itemStateName + " ya existe.");

	}

	@Override
	public ItemStateDTO getItemStateByName(String sessionToken, String itemStateName) throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByName(itemStateName);
		ItemStateDTO itemStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO;
	}
	
	@Override
	public ItemStateDTO getItemStateByOid(String sessionToken, String anOid) throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByOid(anOid);
		ItemStateDTO itemStateDTO = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO;
	}
	
	@Override
	public ItemStateDTO getItemStateByDTO(String sessionToken, ItemStateDTO itemStateDTO) throws UnknownItemStateException {
		ItemState itemState = null;
		itemState = this.getItemStateRepository().getItemStateByDTO(itemStateDTO);
		ItemStateDTO itemStateDTO2 = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(itemState);
		return itemStateDTO2;
	}
	
	public void addNextState(String sessionToken, ItemStateDTO itemStateDTO, ItemStateDTO next) throws UnknownItemStateException,	DTOConcurrencyException {
		ItemState itemState = this.getItemStateRepository().getItemStateByOid(itemStateDTO.getOid());
		ItemState nextItemState = this.getItemStateRepository().getItemStateByOid(next.getOid());
		
		//this.checkDTOConcurrency(itemStateDTO, itemState);
		itemState.addNextState(nextItemState);
	}

	@Override
	public void logicalRemoveItemState(String sessionToken, ItemStateDTO itemStateDTO) throws UnknownItemStateException {
		ItemState itemState = this.getItemStateRepository().getItemStateByDTO(itemStateDTO);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.logicalRemoveItemState(itemState);
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeItemState(String sessionToken, ItemStateDTO aItemStateDTO) throws UnknownItemStateException, DTOConcurrencyException {
			ItemState itemState = this.getItemStateRepository().getItemStateByDTO(aItemStateDTO);
			this.checkDTOConcurrency(aItemStateDTO, itemState);
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			theItemTracker.removeItemState(itemState);
		}
}
