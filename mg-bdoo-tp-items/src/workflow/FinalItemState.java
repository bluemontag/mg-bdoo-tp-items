/**
 * 
 */
package workflow;

import workflow.domain.transition.Transition;

/**
 * @author igallego
 *
 */
public class FinalItemState extends ItemState {

	public FinalItemState(String name) {
		super(name);
	}

	public ItemState executeTransition(Transition t) throws Exception {
		return null;//no hace nada
	}
	
	public Boolean deleted() {
		return false;
	}
	
	public Boolean finalized() {
		return true;
	}
}
