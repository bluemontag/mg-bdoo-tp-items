/**
 * 
 */
package user.service;

import java.util.Collection;
import itemTracker.domain.ItemTracker;
import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserServiceImpl extends AbstractServiceImpl implements UserServiceBI{

	@Override
	public UserDTO createUser(String anUserName, String aPassword) throws UserAlreadyExistsException{
		
		try {
			this.getUserRespository().getUserByUserName(anUserName);
		} catch (UnknownUserException unknownUserException) {			
			ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
			
			User aUser = new User(anUserName, aPassword);
			theItemTracker.addUser(aUser);
			
			UserDTO userDTO = UserDTOFactory.getUserDTO(aUser);
			return userDTO;
		}
		throw new UserAlreadyExistsException("El usuario "+anUserName+" ya existe.");
	}

	@Override
	public Collection<UserDTO> listUsers() {
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		
		Collection<User> users = theItemTracker.getUsers();
		Collection<UserDTO> usersDTOs = UserDTOFactory.getUserDTOList(users);
		
		return usersDTOs;
	}

	@Override
	public UserDTO getUserByUserName(String anUserName) throws UnknownUserException {
		User userToReturn = null;
		userToReturn = this.getUserRespository().getUserByUserName(anUserName);
		UserDTO userDTO = UserDTOFactory.getUserDTO(userToReturn);
		return userDTO;
	}

	@Override
	public UserDTO getUser(UserDTO anUserDTO) throws UnknownUserException {
		return this.getUserByUserName(anUserDTO.getUserName());
	}
	
	@Override
	public void updateUser(UserDTO userToUpdateDTO) throws UnknownUserException, DTOConcurrencyException {
		
		User userToUpdate = this.getUserRespository().getUserByOid(userToUpdateDTO.getOid());
		this.checkDTOConcurrency(userToUpdateDTO, userToUpdate);
		userToUpdate.setPassword(userToUpdateDTO.getPassword());
	}

	@Override
	public void logicalRemoveUserByUserName(String anUserName) throws UnknownUserException {

		User userToRemove = this.getUserRespository().getUserByUserName(anUserName);
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		theItemTracker.logicalRemoveUser(userToRemove);	
	}
	
	@Override
	public void logicalRemoveUser(UserDTO anUserDTO) throws UnknownUserException {

		this.logicalRemoveUserByUserName(anUserDTO.getUserName());
	}
	
	//usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeUser(UserDTO anUserDTO) throws UnknownUserException, DTOConcurrencyException {

		User userToRemove = this.getUserRespository().getUserByUserName(anUserDTO.getUserName());
		this.checkDTOConcurrency(anUserDTO, userToRemove);
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		theItemTracker.removeUser(userToRemove);	
	}

	@Override
	public void setUserAsAdmin(UserDTO anUserDTO) throws UnknownUserException {
		User userToSetAsAdmin = this.getUserRespository().getUserByOid(anUserDTO.getOid());
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		theItemTracker.setAdminUser(userToSetAsAdmin);
	}	
}
