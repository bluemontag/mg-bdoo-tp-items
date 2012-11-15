package user.dto;

import user.domain.User;
import base.dto.AbstractDTOForLists;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserDTOForLists extends AbstractDTOForLists {

	private String userName;
	private String password;
	private final boolean removed;

	public UserDTOForLists(User anUser) {
		super(anUser);
		this.userName = anUser.getUserName();
		this.password = anUser.getPassword();
		this.removed = anUser.isRemoved();
	}

	public UserDTOForLists(UserDTO anUserDTO) {
		super(anUserDTO);
		this.userName = anUserDTO.getUserName();
		this.password = anUserDTO.getPassword();
		this.removed = anUserDTO.isRemoved();
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
}
