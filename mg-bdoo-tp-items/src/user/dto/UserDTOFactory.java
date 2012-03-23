package user.dto;

import user.domain.User;
import base.dto.AbstractDTOFactory;

public class UserDTOFactory extends AbstractDTOFactory {

	static public UserDTO getUserDTO(User aUser){
		return new UserDTO(aUser.getUserName(), aUser.getPassword());
	}
}
