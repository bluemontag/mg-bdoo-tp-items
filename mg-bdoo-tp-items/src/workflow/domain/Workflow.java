/**
 * 
 */
package workflow.domain;

import workflow.domain.state.ItemState;
import base.domain.BaseDomain;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 13/06/2012
 */
public class Workflow extends BaseDomain {

	private String name;
	private ItemState initialState;
	

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
}
