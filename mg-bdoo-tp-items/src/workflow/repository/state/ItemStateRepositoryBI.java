package workflow.repository.state;

import workflow.domain.state.domain.ItemState;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.UnknownItemStateException;


/**
 * @author Ignacio Gallego
 */
public interface ItemStateRepositoryBI {

	public ItemState getItemStateByOid(String anOid) throws UnknownItemStateException;
	public ItemState getItemStateByName(String stateName) throws UnknownItemStateException;
	public ItemState getItemStateByDTO(ItemStateDTO anItemStateDTO) throws UnknownItemStateException;
}
