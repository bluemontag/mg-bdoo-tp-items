package user.dto;

import user.domain.User;

public class UserDTOFactory{

	static public UserDTO getUserDTO(User aUser){
		return new UserDTO(aUser.getUserName(), aUser.getPassword());
	}
}
