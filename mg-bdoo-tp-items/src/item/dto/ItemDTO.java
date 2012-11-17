/**
 * 
 */
package item.dto;

import item.domain.Item;
import item.dto.itemType.ItemTypeDTO;
import item.dto.itemType.ItemTypeDTOFactory;
import user.dto.UserDTO;
import base.dto.AbstractDTO;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 *         14/06/2012
 */
public class ItemDTO extends AbstractDTO {

	protected Long itemNum;
	protected String description;
	protected Integer priority;
	protected String currentState;
	protected ItemTypeDTO itemType;
	protected UserDTO responsible;

	public ItemDTO(Item anItem) {
		super(anItem);

		this.itemNum = anItem.getItemNum();
		this.description = anItem.getDescription();
		this.priority = anItem.getPriority();
		if (anItem.getCurrentState() == null)
			this.currentState = "";
		else {
			this.currentState = anItem.getCurrentState().getName();

		}
		this.itemType = (ItemTypeDTO) ItemTypeDTOFactory.getInstance().getDTO(anItem.getType());

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

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public ItemTypeDTO getType() {
		return itemType;
	}

	public void setType(ItemTypeDTO type) {
		this.itemType = type;
	}

	public UserDTO getResponsible() {
		return responsible;
	}

	public void setResponsible(UserDTO responsible) {
		this.responsible = responsible;
	}
}
