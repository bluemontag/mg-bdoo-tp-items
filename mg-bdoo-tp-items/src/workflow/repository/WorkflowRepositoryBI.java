package workflow.repository;

import workflow.domain.Workflow;
import workflow.exception.UnknownWorkflowException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface WorkflowRepositoryBI {

	Workflow getWorkflowByOid(String anOid) throws UnknownWorkflowException;

}
