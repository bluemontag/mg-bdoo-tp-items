package user.dto;

import java.util.Collection;
import java.util.HashSet;

import user.domain.User;

public class UserDTOFactory{

	static public UserDTO getUserDTO(User aUser){
		return new UserDTO(aUser);
	}

	public static Collection<UserDTO> getUserDTOList(Collection<User> users) {
		Collection<UserDTO> usersDTO = new HashSet<UserDTO>();
		for(User aUser: users){
			UserDTO aUserDTO = UserDTOFactory.getUserDTO(aUser);
			usersDTO.add(aUserDTO);
		}
		return usersDTO;
	}
}
