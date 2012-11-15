package workflow.dto;

import workflow.domain.Workflow;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Ignacio Gallego
 */
public class WorkflowDTOFactory extends DTOFactory {

	private WorkflowDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new WorkflowDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aWorkflow) {
		return new WorkflowDTO((Workflow) aWorkflow);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new WorkflowDTOForLists((Workflow) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO anWorkflowDTO) {
		return new WorkflowDTOForLists((WorkflowDTO) anWorkflowDTO);
	}

}
