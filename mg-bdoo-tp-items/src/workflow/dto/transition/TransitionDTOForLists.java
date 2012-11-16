package workflow.dto.transition;

import workflow.domain.transition.Transition;
import base.dto.AbstractDTOForLists;

/**
 * @author Ignacio Gallego
 */
public class TransitionDTOForLists extends AbstractDTOForLists {

	private String name;

	private final boolean removed;

	public TransitionDTOForLists(Transition anTransition) {
		super(anTransition);
		this.name = anTransition.getName();
		this.removed = anTransition.isRemoved();
	}

	public TransitionDTOForLists(TransitionDTO anTransitionDTO) {
		super(anTransitionDTO);
		this.name = anTransitionDTO.getName();
		this.removed = anTransitionDTO.isRemoved();
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	@Override
	public String toString() {
		return "Transition Name: - " + this.getName() + " - " + (this.removed ? "REMOVED" : "ALIVE");
	}
}
