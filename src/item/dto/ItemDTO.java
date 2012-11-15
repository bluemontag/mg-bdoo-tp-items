/**
 * 
 */
package item.dto;

import item.domain.Item;
import user.dto.UserDTO;
import base.dto.AbstractDTO;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 14/06/2012
 */
public class ItemDTO extends AbstractDTO {

	private Long itemNum;
	private String description;
	private Integer priority;
	private String currentState;
	private String type;
	private UserDTO responsible; 
	
	
	public ItemDTO(Item anItem) {
		super(anItem);
		
		this.itemNum = anItem.getItemNum(); 
		this.description = anItem.getDescription();
		this.priority = anItem.getPriority();
		if (anItem.getCurrentState()==null)
			this.currentState = "";
		else
			this.currentState = anItem.getCurrentState().getName();
		
		if (anItem.getType()!=null) {
			this.type = anItem.getType().getTypeName();	
		}
		
		if (anItem.getResponsible()!=null) {
			UserDTO uDTO = new UserDTO(anItem.getResponsible());
			this.responsible = uDTO;
		}
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
	 * @return the currentState
	 */
	public String getCurrentState() {
		return currentState;
	}

	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the responsible
	 */
	public UserDTO getResponsible() {
		return responsible;
	}

	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(UserDTO responsible) {
		this.responsible = responsible;
	}
}
