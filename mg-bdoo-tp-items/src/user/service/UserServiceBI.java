package user.service;

import java.util.Collection;

import user.dto.UserDTO;
import user.exception.UserAlreadyExistsException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface UserServiceBI {

	UserDTO createUser(String anUserName, String aPassword) throws UserAlreadyExistsException;

	Collection<UserDTO> listUsers();

	void removeUserByName(String anUserName);
	
}
