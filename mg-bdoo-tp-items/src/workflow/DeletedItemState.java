/**
 * 
 */
package workflow;

/**
 * @author igallego
 *
 */
public class DeletedItemState extends ItemState {
	
	public DeletedItemState(String name) {
		super(name);
	}

	public ItemState executeTransition(Transition t) throws Exception {
		return null;//it doesn't do anything
	}

	public Boolean deleted() {
		return true;
	}
	
	public Boolean finalized() {
		return false;
	}
}
