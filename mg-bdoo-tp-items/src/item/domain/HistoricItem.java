/**
 * 
 */
package item.domain;

import java.util.Date;

import base.domain.BaseDomain;

/**
 * @author igallego ignaciogallego@gmail.com
 * 
 */
public class HistoricItem extends BaseDomain {

	private String typeName;
	private Long itemNum;
	private String stateName;
	private String responsibleUserName;
	private Date date;

	public HistoricItem() {
	}

	public HistoricItem(Item item) {
		super();
		this.typeName = item.getType().getTypeName();
		this.itemNum = item.getItemNum();
		this.stateName = item.getCurrentState().getName();
		this.responsibleUserName = item.getResponsible().getUserName();
		this.date = new Date();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String type) {
		this.typeName = type;
	}

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getResponsibleUserName() {
		return responsibleUserName;
	}

	public void setResponsibleUserName(String responsibleUserName) {
		this.responsibleUserName = responsibleUserName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
