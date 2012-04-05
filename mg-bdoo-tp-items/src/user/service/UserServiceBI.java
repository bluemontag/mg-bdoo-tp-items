package user.service;

import java.util.Collection;


import user.domain.User;
import user.dto.UserDTO;

public interface UserServiceBI {

	UserDTO createUser(String anUserName, String aPassword);

	Collection<UserDTO> listUsers();

	void removeUserByName(String anUserName);
	
}
