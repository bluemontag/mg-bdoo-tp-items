/**
 * 
 */
package user.service;

import java.util.Collection;
import itemTracker.domain.ItemTracker;
import itemTracker.exception.UnknownUserException;
import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo
 *
 */
public class UserServiceImpl extends AbstractServiceImpl implements UserServiceBI{

	@Override
	public UserDTO createUser(String anUserName, String aPassword){
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		
		User aUser = new User(anUserName, aPassword);
		theItemTracker.addUser(aUser);
		
		UserDTO userDTO = UserDTOFactory.getUserDTO(aUser);
		return userDTO;
	}

	@Override
	public Collection<UserDTO> listUsers() {
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		
		Collection<User> users = theItemTracker.getUsers();
		Collection<UserDTO> usersDTOs = UserDTOFactory.getUserDTOList(users);
		
		return usersDTOs;
	}

	@Override
	public void removeUserByName(String anUserName) {
		User userToRemove = null;
		// TODO: ver como hacer con esto!!
		try {
			userToRemove = this.getUserRespository().getUserByUserName(anUserName);
		} catch (UnknownUserException e) {
			e.printStackTrace();
		}
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		theItemTracker.removeUser(userToRemove);	
	}
}
