/**
 * 
 */
package item.domain;

import item.domain.itemType.ItemType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import user.domain.User;
import workflow.domain.state.ItemState;
import workflow.exception.transition.BadTransitionException;
import base.domain.BaseDomain;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 */
public class Item extends BaseDomain {

	protected Long itemNum;
	protected String description;
	protected Integer priority;
	protected ItemState currentState;
	protected ItemType type;
	protected User responsible; // (se escribe asi "responsible")
	protected Collection<HistoricItem> history = new ArrayList<HistoricItem>();

	public Item() {
		// para hibernate
	}

	public Item(String description, Integer priority, ItemType type, User responsible, ItemState firstState) {
		super();
		this.description = description;
		this.priority = priority;
		this.type = type;
		this.responsible = responsible;
		this.currentState = firstState;
	}

	/**
	 * Takes a snapshot of the current state of the Item, and saves it for
	 * further reference.
	 * 
	 * Toma una instantanea del estado actual del Item, y la guarda para futura
	 * referencia.
	 * 
	 * @throws Exception
	 */
	public HistoricItem saveItem() throws Exception {
		HistoricItem hi = new HistoricItem(this);

		// save in history
		this.history.add(hi);

		return hi;

	}

	/**
	 * @throws BadTransitionException
	 */
	public void executeTransition(String transitionCode) throws BadTransitionException {
		this.getCurrentState().executeTransition(transitionCode, this);
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public ItemState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ItemState state) {
		this.currentState = state;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public Collection<HistoricItem> getHistory() {
		return history;
	}

	public void setHistory(List<HistoricItem> history) {
		this.history = history;
	}
}