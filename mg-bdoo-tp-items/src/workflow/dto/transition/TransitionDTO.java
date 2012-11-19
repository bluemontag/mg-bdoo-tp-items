package workflow.dto.transition;

import workflow.domain.transition.Transition;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TransitionDTO extends AbstractDTO {

	private String name;
	private final String transitionCode;
	private boolean removed;

	public TransitionDTO(Transition aTransition) {
		super(aTransition);
		this.name = aTransition.getName();
		this.transitionCode = aTransition.getTransitionCode();
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

}
