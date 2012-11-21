/**
 * 
 */
package item.domain.historicItem;

import item.domain.Item;

import java.util.Date;

import user.domain.User;
import workflow.domain.state.ItemState;
import base.domain.BaseDomain;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 */
public class HistoricItem extends BaseDomain {

	private Item item;
	private ItemState itemState;
	private User responsibleUser;
	private Date date;

	public HistoricItem() {
	}

	public HistoricItem(Item item, User anUser) {
		super();
		this.item = item;
		this.itemState = item.getCurrentState();
		this.setResponsibleUser(anUser);
		this.date = new Date();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemState getItemState() {
		return itemState;
	}

	public void setItemState(ItemState itemState) {
		this.itemState = itemState;
	}

	public User getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(User responsibleUser) {
		this.responsibleUser = responsibleUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
