package workflow.repository;

import workflow.domain.Workflow;
import workflow.exception.UnknownWorkflowException;
import base.repository.HibernateBaseRepository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class HibernateWorkflowRepository extends HibernateBaseRepository implements WorkflowRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Workflow.class;
	}

	@Override
	public Workflow getWorkflowByOid(String anOid) throws UnknownWorkflowException {
		Workflow aWorkflow = (Workflow) this.findeByOid(this.getEntityClass(), anOid);
		if (aWorkflow == null) {
			throw new UnknownWorkflowException("No se encuentra el workflow buscado.");
		}
		return aWorkflow;
	}

}
