/**
 * 
 */
package item.dto.historicItem;

import item.domain.historicItem.HistoricItem;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;

import java.util.Date;

import user.dto.UserDTO;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class HistoricItemDTO extends AbstractDTO {

	private final ItemDTO item;
	private final UserDTO responsibleUser;
	private final Date date;
	private final ItemStateDTO itemState;

	public HistoricItemDTO(HistoricItem aHistoricItem) {
		super(aHistoricItem);
		this.item = (ItemDTO) ItemDTOFactory.getInstance().getDTO(aHistoricItem.getItem());
		this.responsibleUser = (UserDTO) HistoricItemDTOFactory.getInstance()
				.getDTO(aHistoricItem.getResponsibleUser());
		this.date = aHistoricItem.getDate();
		this.itemState = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(aHistoricItem);

	}

	public UserDTO getResponsibleUser() {
		return responsibleUser;
	}

	public ItemDTO getItem() {
		return item;
	}

	public Date getDate() {
		return date;
	}

	public ItemStateDTO getItemState() {
		return itemState;
	}

}
