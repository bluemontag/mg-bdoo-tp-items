package workflow.repository;

import workflow.domain.Workflow;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;

/**
 * @author Ignacio Gallego
 */
public class MemoryWorkflowRepository implements WorkflowRepositoryBI {

	@Override
	public Workflow getWorkflowByOid(String oid) throws UnknownWorkflowException {
		// Devolver el workflow desde lo que esta cargado en memoria.
		return null;
	}

	@Override
	public Workflow getWorkflowByName(String workflowName) throws UnknownWorkflowException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workflow getWorkflowByDTO(WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException {
		// TODO Auto-generated method stub
		return null;
	}
}
