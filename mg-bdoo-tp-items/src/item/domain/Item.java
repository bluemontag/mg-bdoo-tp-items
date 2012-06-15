/**
 * 
 */
package item.domain;

import java.util.Date;

import user.domain.User;
import workflow.ItemState;
import workflow.Transition;
import base.domain.BaseDomain;

/**
 * @author igallego
 *
 */
public class Item extends BaseDomain {

	private Long itemNum;
	private String description;
	private Integer priority;
	private ItemState currentState;
	private ItemType type;
	private User responsible; //(se escribe asi "responsible")
	

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
		
		//guardar en base aca???
		
		return hi;
		
	}
	
	/**
	 * Executes the transition and saves the new current state, 
	 *  obtained from the transition.
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void executeTransition(Transition t) throws Exception {
		this.setCurrentState(this.getCurrentState().executeTransition(t));
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
	
}
