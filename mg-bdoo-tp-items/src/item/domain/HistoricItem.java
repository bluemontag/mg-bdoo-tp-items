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

	
	/**
	 * @return the type
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param type the type to set
	 */
	public void setTypeName(String type) {
		this.typeName = type;
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
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the responsibleUserName
	 */
	public String getResponsibleUserName() {
		return responsibleUserName;
	}
	/**
	 * @param responsibleUserName the responsibleUserName to set
	 */
	public void setResponsibleUserName(String responsibleUserName) {
		this.responsibleUserName = responsibleUserName;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
