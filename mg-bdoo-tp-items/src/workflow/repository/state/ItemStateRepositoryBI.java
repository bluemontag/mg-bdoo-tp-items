package workflow.repository.state;

import workflow.domain.state.ItemState;
import workflow.dto.WorkflowDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.exception.state.UnknownItemStateException;

/**
 * @author Ignacio Gallego
 */
public interface ItemStateRepositoryBI {

	public ItemState getItemStateByOid(String anOid) throws UnknownItemStateException;

	public ItemState getItemStateByNameAndWorkflow(WorkflowDTO aWorkflowDTO, String stateName)
			throws UnknownItemStateException;

	public ItemState getItemStateByDTO(ItemStateDTO anItemStateDTO) throws UnknownItemStateException;
}
