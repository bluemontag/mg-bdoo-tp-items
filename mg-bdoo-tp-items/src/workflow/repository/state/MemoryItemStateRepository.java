package workflow.repository.state;

import workflow.domain.state.ItemState;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.UnknownItemStateException;


/**
 * @author Ignacio Gallego
 */
public class MemoryItemStateRepository implements ItemStateRepositoryBI {

	@Override
	public ItemState getItemStateByOid(String oid) throws UnknownItemStateException {
		return null;
	}

	@Override
	public ItemState getItemStateByName(String workflowName) throws UnknownItemStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemState getItemStateByDTO(ItemStateDTO aItemStateDTO) throws UnknownItemStateException {
		// TODO Auto-generated method stub
		return null;
	}
}
