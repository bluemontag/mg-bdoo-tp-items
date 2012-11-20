package workflow.dto.transition;

import workflow.domain.transition.Transition;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TransitionDTO extends AbstractDTO {

	private String name;
	private final String transitionCode;
	private boolean removed;
	private final ItemStateDTO nextState;

	public TransitionDTO(Transition aTransition) {
		super(aTransition);
		this.name = aTransition.getName();
		this.transitionCode = aTransition.getTransitionCode();
		this.nextState = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(aTransition.getNextState());
		this.removed = aTransition.isRemoved();
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

	public String getTransitionCode() {
		return transitionCode;
	}

	public ItemStateDTO getNextState() {
		return nextState;
	}

}
