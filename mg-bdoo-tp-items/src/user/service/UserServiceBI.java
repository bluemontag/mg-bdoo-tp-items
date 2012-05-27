package user.service;

import java.util.Collection;

import base.exception.DTOConcurrencyException;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface UserServiceBI {

	// Creats
	
	UserDTO createUser(String anUserName, String aPassword) throws UserAlreadyExistsException;

	// Lists
	
	Collection<UserDTO> listUsers();
	
	// Retrives
	
	UserDTO getUserByUserName(String anUserName) throws UnknownUserException;
	
	UserDTO getUser(UserDTO anUserDTO) throws UnknownUserException;

	// Updates
	
	void updateUser(UserDTO userToUpdateDTO) throws UnknownUserException, DTOConcurrencyException;
	
	void setUserAsAdmin(UserDTO anUser) throws UnknownUserException;
	
	// Removes
	
	void logicalRemoveUserByUserName(String anUserName) throws UnknownUserException;
	
	void logicalRemoveUser(UserDTO aUserDTOToRemove) throws UnknownUserException;
	
	// usado por los tests para dejar la base como estaba
	@Deprecated
	void removeUser(UserDTO aUserDTOToRemove) throws UnknownUserException, DTOConcurrencyException;
}
