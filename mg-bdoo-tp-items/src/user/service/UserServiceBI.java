package user.service;

import java.util.Collection;

import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface UserServiceBI {

	// Creats

	UserDTO createUser(String sessionToken, String anUserName, String aPassword) throws UserAlreadyExistsException;

	// Lists

	Collection<UserDTOForLists> listUsers(String sessionToken);

	// Retrives

	UserDTO getUserByUserName(String sessionToken, String anUserName) throws UnknownUserException;

	UserDTO getUser(String sessionToken, UserDTO anUserDTO) throws UnknownUserException;

	// Updates

	void updateUser(String sessionToken, UserDTO userToUpdateDTO) throws UnknownUserException, DTOConcurrencyException;

	void setUserAsAdmin(String sessionToken, UserDTO anUser) throws UnknownUserException;

	// Removes

	void logicalRemoveUserByUserName(String sessionToken, String anUserName) throws UnknownUserException;

	void logicalRemoveUser(String sessionToken, UserDTO aUserDTOToRemove) throws UnknownUserException;

	// usado por los tests para dejar la base como estaba
	@Deprecated
	void removeUser(String sessionToken, UserDTO aUserDTOToRemove) throws UnknownUserException, DTOConcurrencyException;
}
