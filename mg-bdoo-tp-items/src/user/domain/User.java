package user.domain;

import base.domain.BaseDomain;

public class User extends BaseDomain{

	private String userName;
	private String password;
	private boolean removed = false;
	
	public User() {
		// Si no esta este contructor, hibernate no funciona.
	}
	public User(String anUserName, String aPassword) {
		this.userName = anUserName;
		this.password = aPassword;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param anUserName the userName to set
	 */
	public void setUserName(String anUserName) {
		this.userName = anUserName;
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

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public boolean isRemoved() {
		return removed;
	}
}
