package workflow.repository;

import workflow.domain.Workflow;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;

/**
 * @author Ignacio Gallego
 */
public interface WorkflowRepositoryBI {

	public Workflow getWorkflowByOid(String anOid) throws UnknownWorkflowException;
	public Workflow getWorkflowByName(String workflowName) throws UnknownWorkflowException;
	public Workflow getWorkflowByDTO(WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException;
}
