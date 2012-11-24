/**
 * 
 */
package item.dto.historicItem;

import item.domain.historicItem.HistoricItem;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;

import java.util.Date;

import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import workflow.dto.state.ItemStateDTO;
import workflow.dto.state.ItemStateDTOFactory;
import base.dto.AbstractDTOForLists;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */

public class HistoricItemDTOForLists extends AbstractDTOForLists {

	private final ItemDTO item;
	private final Date date;
	private final UserDTO responsibleUser;
	private final ItemStateDTO itemState;

	public HistoricItemDTOForLists(HistoricItemDTO anHistoricItemDTO) {
		super(anHistoricItemDTO);
		this.item = anHistoricItemDTO.getItem();
		this.responsibleUser = anHistoricItemDTO.getResponsibleUser();
		this.date = anHistoricItemDTO.getDate();
		this.itemState = anHistoricItemDTO.getItemState();

	}

	public HistoricItemDTOForLists(HistoricItem anHistoricItem) {
		super(anHistoricItem);
		this.item = (ItemDTO) ItemDTOFactory.getInstance().getDTO(anHistoricItem.getItem());
		this.responsibleUser = (UserDTO) UserDTOFactory.getInstance().getDTO(anHistoricItem.getResponsibleUser());
		this.date = anHistoricItem.getDate();
		if (anHistoricItem.getItemState() != null) {
			this.itemState = (ItemStateDTO) ItemStateDTOFactory.getInstance().getDTO(anHistoricItem.getItemState());
		} else {
			this.itemState = null;
		}
	}

	public ItemDTO getItem() {
		return item;
	}

	public UserDTO getResponsibleUser() {
		return responsibleUser;
	}

	public Date getDate() {
		return date;
	}

	public ItemStateDTO getItemState() {
		return itemState;
	}

	@Override
	public String toString() {
		return "<Nro: " + item.getItemNum() + "><Date: " + date + "><Responsable: " + responsibleUser.getUserName()
				+ "><HistoricState: " + itemState != null ? itemState.getName() : "" + ">";
	}
}
