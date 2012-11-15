package workflow.dto;


import workflow.domain.Workflow;
import base.dto.AbstractDTOForLists;

/**
 * @author Ignacio Gallego
 */
public class WorkflowDTOForLists extends AbstractDTOForLists {

	private String name;
	
	private final boolean removed;

	public WorkflowDTOForLists(Workflow workflow) {
		super(workflow);
		this.name = workflow.getName();
		this.removed = workflow.isRemoved();
	}

	public WorkflowDTOForLists(WorkflowDTO workflowDTO) {
		super(workflowDTO);
		this.name = workflowDTO.getName();
		this.removed = workflowDTO.isRemoved();
	}

	/**
	 * @return the userName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setName(String userName) {
		this.name = userName;
	}
	
	@Override
	public String toString() {
		return "Workflow Name: - " + this.getName() + " - " + (this.removed ? "REMOVED" : "ALIVE");
	}
}
