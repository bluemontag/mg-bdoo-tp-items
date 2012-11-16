/**
 * 
 */
package workflow.domain;

import java.util.ArrayList;
import java.util.Collection;

import workflow.domain.state.ItemState;
import workflow.exception.state.UnknownItemStateException;
import base.domain.BaseDomain;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         13/06/2012
 */
public class Workflow extends BaseDomain {

	protected String name;
	protected ItemState initialState;
	protected Collection<ItemState> itemStates;

	public Workflow() {
		this.itemStates = new ArrayList<ItemState>();
	}

	public Workflow(String name) {
		super();
		this.name = name;
		this.itemStates = new ArrayList<ItemState>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemState getInitialState() {
		return initialState;
	}

	public void setInitialState(ItemState initialState) {
		this.initialState = initialState;
	}

	public Collection<ItemState> getItemStates() {
		return itemStates;
	}

	public void setItemStates(Collection<ItemState> states) {
		this.itemStates = states;
	}

	public void addItemState(ItemState anItemState) {
		this.itemStates.add(anItemState);
	}

	public void removeItemState(ItemState anItemState) throws UnknownItemStateException {
		boolean removed = this.itemStates.remove(anItemState);
		if (!removed) {
			throw new UnknownItemStateException("El estado del item que desea eliminar no existe.");
		}
		if (anItemState.equals(this.initialState)) {
			this.initialState = null;
		}
	}
}
