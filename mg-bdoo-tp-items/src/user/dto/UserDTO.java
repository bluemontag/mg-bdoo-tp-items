package user.dto;

import user.domain.User;

public class UserDTO {

	protected String userName;
	protected String password;

	public UserDTO(User aUser) {
		this.userName = aUser.getUserName();
		this.password = aUser.getPassword();
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

}
