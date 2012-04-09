package user.domain;

import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class User extends BaseDomain{

	protected String userName;
	protected String password;
	
	// se utiliza para saber si un usuario fue eliminado.
	private boolean removed = false;
	
	public User() {
		// Si no esta este contructor, hibernate no funciona.
	}
	
	public User(String anUserName, String aPassword) {
		this.userName = anUserName;
		this.password = aPassword;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String anUserName) {
		this.userName = anUserName;
	}

	public String getPassword() {
		return password;
	}

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
