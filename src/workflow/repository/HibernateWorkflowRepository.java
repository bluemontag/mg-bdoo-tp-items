package workflow.repository;

import workflow.domain.Workflow;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;
import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

/**
 * @author Ignacio Gallego
 */
public class HibernateWorkflowRepository extends HibernateBaseRepository implements WorkflowRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Workflow.class;
	}

	@Override
	public Workflow getWorkflowByOid(String anOid) throws UnknownWorkflowException {
		Workflow aWorkflow = (Workflow) this.findByOid(this.getEntityClass(), anOid);
		if (aWorkflow == null) {
			throw new UnknownWorkflowException("No se encuentra el workflow buscado.");
		}
		return aWorkflow;
	}

	@Override
	public Workflow getWorkflowByName(String workflowName) throws UnknownWorkflowException {
		Workflow workflow = null;
		try {
			workflow = (Workflow) this.getEntityByUniqueField("getWorkflowByName", "aWorkflowName", workflowName);
		} catch (BaseException aBaseException) {
			// TODO ver que hacer!!
		}
		if (workflow == null) {
			throw new UnknownWorkflowException("El workflow " + workflowName + " no existe.");
		}
		return workflow;		
	}
	
	@Override
	public Workflow getWorkflowByDTO(WorkflowDTO aWorkflowDTO) throws UnknownWorkflowException {
		return this.getWorkflowByOid(aWorkflowDTO.getOid());
	}
}
