package user.dto;

import user.domain.User;
import base.dto.AbstractDTOForLists;

public class UserDTOForLists extends AbstractDTOForLists{

	private String userName;
	private String password;
	private boolean removed;

	
	public UserDTOForLists(User anUser) {
		super(anUser);
		this.userName = anUser.getUserName();
		this.password = anUser.getPassword();
		this.removed = anUser.isRemoved();
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return "UserName: - "+this.getUserName()+" - "+ (this.removed? "REMOVED": "ALIVE");
	}
}
