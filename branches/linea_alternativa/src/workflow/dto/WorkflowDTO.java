package workflow.dto;

import workflow.domain.Workflow;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class WorkflowDTO extends AbstractDTO {

	private String name;
	private boolean removed;
	
	public WorkflowDTO(Workflow aWorkflow) {
		super(aWorkflow);
		this.name = aWorkflow.getName();
		this.removed = aWorkflow.isRemoved();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

}
