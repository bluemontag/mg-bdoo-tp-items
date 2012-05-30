package user.dto;

import user.domain.User;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserDTO extends AbstractDTO {

	protected String userName;
	protected String password;
	protected boolean removed;

	public UserDTO(User anUser) {
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
	 * @param userName
	 *            the userName to set
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserName: - " + this.getUserName() + " - " + (this.removed ? "REMOVED" : "ALIVE");
	}

	public boolean isRemoved() {
		return this.removed;
	}
}
