package user.service;

import user.dto.UserDTO;

public interface UserServiceBI {

	UserDTO createUser(String anUserName, String aPassword);
	
}
