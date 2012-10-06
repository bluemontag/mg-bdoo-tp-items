/**
 * 
 */
package item.domain;

import item.domain.itemType.ItemType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

	private Long itemNum;
	private String description;
	private Integer priority;
	private ItemState currentState;
	private ItemType type;
	private User responsible; //(se escribe asi "responsible")
	private Collection<HistoricItem> history = new ArrayList<HistoricItem>();
	

	@Deprecated
	public Item(Long itemNum, String typeName) {
		/* Creation of an item without workflow... is useless! */
		this(itemNum, "", 1, new ItemType(typeName, null, null));
	}
	
	public Item() {
		//para hibernate
	}
	public Item(Long itemNum, String description, Integer priority, ItemType type) {
		this.itemNum = itemNum; 
		this.description = description;
		this.priority = priority;
		this.type = type;
		this.responsible = null;
		this.currentState = null; //new ItemState("new");
	}
	/**
	 * Takes a snapshot of the current state of the Item, and saves it for
	 *  further reference.
	 * 
	 * Toma una instantanea del estado actual del Item, y la guarda
	 *  para futura referencia.
	 *   
	 * @throws Exception
	 */
	public HistoricItem saveItem() throws Exception {
		HistoricItem hi = new HistoricItem();
		hi.setDate(new Date());
		hi.setItemNum(this.getItemNum());
		hi.setResponsibleUserName(this.getResponsible().getUserName());
		hi.setStateName(this.getCurrentState().getName());
		hi.setTypeName(this.getType().getTypeName());
		
		//save in history
		this.history.add(hi);
		
		return hi;
		
	}
	
	/**
	 * Executes the transition and saves the new current state, 
	 *  obtained from the transition.
	 * 
	 * @param t
	 * @throws BadTransitionException
	 */
	public ItemState executeTransition(String t) throws BadTransitionException {
		ItemState newState = this.getCurrentState().executeTransition(t);
		this.setCurrentState(newState);
		return newState;
	}
	/**
	 * @return the type
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ItemType type) {
		this.type = type;
	}

	/**
	 * @return the itemNum
	 */
	public Long getItemNum() {
		return itemNum;
	}

	/**
	 * @param itemNum the itemNum to set
	 */
	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the state
	 */
	public ItemState getCurrentState() {
		return currentState;
	}

	/**
	 * @param state the state to set
	 */
	public void setCurrentState(ItemState state) {
		this.currentState = state;
	}

	/**
	 * @return the responsible
	 */
	public User getResponsible() {
		return responsible;
	}

	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	/**
	 * @return the history
	 */
	public Collection<HistoricItem> getHistory() {
		return history;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(List<HistoricItem> history) {
		this.history = history;
	}
}