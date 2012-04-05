package user.service;

import java.util.Collection;


import user.dto.UserDTO;

public interface UserServiceBI {

	UserDTO createUser(String anUserName, String aPassword);
	
	// Todo: StrategyOrder?
	Collection<UserDTO> listUsers();
	
}
