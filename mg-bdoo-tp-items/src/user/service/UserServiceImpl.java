/**
 * 
 */
package user.service;

import itemTracker.domain.ItemTracker;
import user.domain.User;
import user.dto.UserDTO;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo
 *
 */
public class UserServiceImpl extends AbstractServiceImpl implements UserServiceBI{

	public UserDTO createUser(String anUserName, String aPassword){
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		
		User aUser = new User(anUserName, aPassword);
		theItemTracker.addUser(aUser);
		
		return new UserDTO("rodrigo", "rodrigo");
	}
}
