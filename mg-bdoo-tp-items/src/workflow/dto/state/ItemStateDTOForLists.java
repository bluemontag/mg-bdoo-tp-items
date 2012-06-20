package workflow.dto.state;


import workflow.domain.state.domain.ItemState;
import base.dto.AbstractDTOForLists;

/**
 * @author Ignacio Gallego (ignaciogallego@gmail.com)
 */
public class ItemStateDTOForLists extends AbstractDTOForLists {

	private String name;
	
	public ItemStateDTOForLists(ItemState anItemState) {
		super(anItemState);
	}

	public ItemStateDTOForLists(ItemStateDTO anItemStateDTO) {
		super(anItemStateDTO);
		this.setName(anItemStateDTO.getName());
	}

	@Override
	public String toString() {
		return "ItemState: " + this.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
