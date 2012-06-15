package workflow.domain.transition;

import workflow.ItemState;
import base.domain.BaseDomain;

public class Transition extends BaseDomain {

	private String name;//nombre de la transicion, en caso de necesitarlo.
	private ItemState initialState;
	private ItemState finalState;
	/**
	 * @return the initialState
	 */
	public ItemState getInitialState() {
		return initialState;
	}
	/**
	 * @param initialState the initialState to set
	 */
	public void setInitialState(ItemState initialState) {
		this.initialState = initialState;
	}
	/**
	 * @return the finalState
	 */
	public ItemState getFinalState() {
		return finalState;
	}
	/**
	 * @param finalState the finalState to set
	 */
	public void setFinalState(ItemState finalState) {
		this.finalState = finalState;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
