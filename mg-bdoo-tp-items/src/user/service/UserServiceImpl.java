/**
 * 
 */
package user.service;

import itemTracker.domain.ItemTracker;

import java.util.Collection;

import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserServiceImpl extends AbstractServiceImpl implements UserServiceBI {

	@Override
	public UserDTO createUser(String sessionToken, String anUserName, String aPassword)
			throws UserAlreadyExistsException {

		try {
			this.getUserRepository().getUserByUserName(anUserName);
		} catch (UnknownUserException unknownUserException) {
			ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
			String aPasswordEncrypted = aPassword; // TODO: encriptar
			User aUser = new User(anUserName, aPasswordEncrypted);
			theItemTracker.addUser(aUser);

			UserDTO userDTO = (UserDTO) UserDTOFactory.getInstance().getDTO(aUser);
			return userDTO;
		}
		throw new UserAlreadyExistsException("El usuario " + anUserName + " ya existe.");
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<UserDTOForLists> listUsers(String sessionToken) {
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();

		Collection<User> users = theItemTracker.getUsers();
		Collection<UserDTOForLists> usersDTOForLists = (Collection<UserDTOForLists>) UserDTOFactory.getInstance()
				.getDTOList(users);

		return usersDTOForLists;
	}

	@Override
	public UserDTO getUserByUserName(String sessionToken, String anUserName) throws UnknownUserException {
		User userToReturn = null;
		userToReturn = this.getUserRepository().getUserByUserName(anUserName);
		UserDTO userDTO = (UserDTO) UserDTOFactory.getInstance().getDTO(userToReturn);
		return userDTO;
	}

	@Override
	public UserDTO getUser(String sessionToken, UserDTO anUserDTO) throws UnknownUserException {
		return this.getUserByUserName(sessionToken, anUserDTO.getUserName());
	}

	@Override
	public void updateUser(String sessionToken, UserDTO userToUpdateDTO) throws UnknownUserException,
			DTOConcurrencyException {

		User userToUpdate = this.getUserRepository().getUserByOid(userToUpdateDTO.getOid());
		this.checkDTOConcurrency(userToUpdateDTO, userToUpdate);
		userToUpdate.setPassword(userToUpdateDTO.getPassword());
	}

	@Override
	public void logicalRemoveUserByUserName(String sessionToken, String anUserName) throws UnknownUserException {

		User userToRemove = this.getUserRepository().getUserByUserName(anUserName);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.logicalRemoveUser(userToRemove);
	}

	@Override
	public void logicalRemoveUser(String sessionToken, UserDTO anUserDTO) throws UnknownUserException {

		this.logicalRemoveUserByUserName(sessionToken, anUserDTO.getUserName());
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	@Override
	public void removeUser(String sessionToken, UserDTO anUserDTO) throws UnknownUserException, DTOConcurrencyException {

		User userToRemove = this.getUserRepository().getUserByUserName(anUserDTO.getUserName());
		this.checkDTOConcurrency(anUserDTO, userToRemove);
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.removeUser(userToRemove);
	}

	@Override
	public void setUserAsAdmin(String sessionToken, UserDTO anUserDTO) throws UnknownUserException {
		User userToSetAsAdmin = this.getUserRepository().getUserByOid(anUserDTO.getOid());
		ItemTracker theItemTracker = this.getItemTrackerRepository().getItemTracker();
		theItemTracker.setAdminUser(userToSetAsAdmin);
	}
}
