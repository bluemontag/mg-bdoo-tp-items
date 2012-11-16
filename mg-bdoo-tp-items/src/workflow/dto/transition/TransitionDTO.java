package workflow.dto.transition;

import workflow.domain.Workflow;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TransitionDTO extends AbstractDTO {

	private String name;
	private boolean removed;
	
	public TransitionDTO(Workflow aWorkflow) {
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
