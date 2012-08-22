/**
 * 
 */
package workflow.dto.state;

import workflow.domain.state.domain.ItemState;
import base.dto.AbstractDTO;

/**
 * @author igallego
 *
 */
public class ItemStateDTO extends AbstractDTO {

	private String name;
	
	public ItemStateDTO(ItemState aState) {
		super(aState);
		this.setName(aState.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
