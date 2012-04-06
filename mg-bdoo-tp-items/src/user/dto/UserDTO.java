package user.dto;

import base.dto.AbstractDTO;
import user.domain.User;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserDTO extends AbstractDTO{

	protected String userName;
	protected String password;
	protected boolean removed;

	public UserDTO(User aUser) {
		this.id = aUser.getId();
		this.userName = aUser.getUserName();
		this.password = aUser.getPassword();
		this.removed = aUser.isRemoved(); 
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
