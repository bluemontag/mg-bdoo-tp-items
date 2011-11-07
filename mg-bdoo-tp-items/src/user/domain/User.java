package user.domain;

import base.domain.BaseDomain;

public class User extends BaseDomain{

	private String userName;
	private String password;
	

	public User(String aUserName, String aPassword) {
		this.userName = aUserName;
		this.password = aPassword;
	}

	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param aUserName the userName to set
	 */
	public void setUserName(String aUserName) {
		this.userName = aUserName;
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
	public void setPassword(String aPassword) {
		this.password = aPassword;
	}
}
