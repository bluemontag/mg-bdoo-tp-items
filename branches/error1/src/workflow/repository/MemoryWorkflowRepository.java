package workflow.repository;

import workflow.domain.Workflow;
import workflow.exception.UnknownWorkflowException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class MemoryWorkflowRepository implements WorkflowRepositoryBI {

	@Override
	public Workflow getWorkflowByOid(String oid) throws UnknownWorkflowException {
		// Devolver el workflow desde lo que esta cargado en memoria.
		return null;
	}
}
