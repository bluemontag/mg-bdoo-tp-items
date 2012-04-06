package user.domain;

import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class User extends BaseDomain{

	private Long id;
	private Integer version;
	private String userName;
	private String password;
	
	// se utiliza para saber si un usuario fue eliminado.
	private boolean removed = false;
	
	public User() {
		// Si no esta este contructor, hibernate no funciona.
	}
	
	public User(String anUserName, String aPassword) {
		this.userName = anUserName;
		this.password = aPassword;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}	

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
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
