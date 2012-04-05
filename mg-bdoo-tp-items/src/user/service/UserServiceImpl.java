/**
 * 
 */
package user.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import itemTracker.domain.ItemTracker;
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
}
