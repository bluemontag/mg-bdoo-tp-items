package user.service;

import java.util.Collection;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface UserServiceBI {

	UserDTO createUser(String anUserName, String aPassword) throws UserAlreadyExistsException;

	Collection<UserDTO> listUsers();

	void removeUserByUserName(String anUserName) throws UnknownUserException;
	
	UserDTO getUserByUserName(String anUserName) throws UnknownUserException;

	void updateUser(UserDTO userToUpdateDTO) throws UnknownUserException;
	
}
