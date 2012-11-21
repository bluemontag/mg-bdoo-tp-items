/**
 * 
 */
package item.dto.historicItem;

import item.domain.historicItem.HistoricItem;
import item.dto.ItemDTO;
import item.dto.ItemDTOFactory;
import user.dto.UserDTO;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class HistoricItemDTO extends AbstractDTO {

	protected ItemDTO item;
	protected UserDTO responsibleUser;

	public HistoricItemDTO(HistoricItem aHistoricItem) {
		super(aHistoricItem);
		this.item = (ItemDTO) ItemDTOFactory.getInstance().getDTO(aHistoricItem.getItem());
	}

	public UserDTO getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(UserDTO responsibleUser) {
		this.responsibleUser = responsibleUser;
	}
}
