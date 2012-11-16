package workflow.domain.transition;

import workflow.domain.state.ItemState;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class Transition extends BaseDomain {

	protected String name;
	protected String transitionCode;
	protected ItemState nextState;

	public Transition() {
		// hibernate
	}

	public Transition(String name, String transitionCode, ItemState nextState) {
		super();
		this.name = name;
		this.transitionCode = transitionCode;
		this.nextState = nextState;
	}

	public ItemState getNextState() {
		return nextState;
	}

	public void setNextState(ItemState nextState) {
		this.nextState = nextState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransitionCode() {
		return transitionCode;
	}

	public void setTransitionCode(String transitionCode) {
		this.transitionCode = transitionCode;
	}

	public boolean isThisTransition(String aTransitionCode) {
		boolean isTransition = this.transitionCode.equals(aTransitionCode);
		return isTransition;
	}

}
