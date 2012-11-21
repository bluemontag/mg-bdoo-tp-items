/**
 * 
 */
package item.domain;

import item.domain.historicItem.HistoricItem;
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
	protected Collection<HistoricItem> history;

	public Item() {
		// para hibernate
	}

	public Item(long itemNum, String description, Integer priority, ItemType type, User responsible,
			ItemState firstState) {
		super();
		this.itemNum = itemNum;
		this.description = description;
		this.priority = priority;
		this.type = type;
		this.responsible = responsible;
		this.currentState = firstState;
		this.history = new ArrayList<HistoricItem>();
	}

	public HistoricItem saveItem(User anUser) {
		HistoricItem hi = new HistoricItem(this, anUser);
		this.history.add(hi);
		return hi;

	}

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

	public void historyEmpty() {
		this.history.clear();
	}
}