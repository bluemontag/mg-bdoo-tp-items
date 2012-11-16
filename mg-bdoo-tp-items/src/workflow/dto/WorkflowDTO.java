package workflow.dto;

import java.util.Collection;

import workflow.domain.Workflow;
import workflow.domain.state.ItemState;
import workflow.dto.state.ItemStateDTOFactory;
import workflow.dto.state.ItemStateDTOForLists;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class WorkflowDTO extends AbstractDTO {

	private String name;
	private boolean removed;
	private final Collection<ItemStateDTOForLists> itemStates;
	private final ItemState initialState;

	@SuppressWarnings("unchecked")
	public WorkflowDTO(Workflow aWorkflow) {
		super(aWorkflow);
		this.name = aWorkflow.getName();
		this.removed = aWorkflow.isRemoved();
		this.initialState = aWorkflow.getInitialState();
		this.itemStates = (Collection<ItemStateDTOForLists>) ItemStateDTOFactory.getInstance()
				.getDTOsForListFromBaseDomains(aWorkflow.getItemStates());
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

	public Collection<ItemStateDTOForLists> getItemStates() {
		return itemStates;
	}

	public ItemState getInitialState() {
		return initialState;
	}

}
