package itemTracker.domain;

import java.util.Collection;
import java.util.HashSet;

import base.domain.BaseDomain;

import user.domain.User;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTracker extends BaseDomain{
	
	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
		
	private Collection<User> users = new HashSet<User>();

	public ItemTracker(){
		this.users = new HashSet<User>();
	}
	
	/**
	 * @return the users
	 */
	public void addUser(User aUser) {
		this.users.add(aUser);
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	/**
	 * @returns users
	 */
	public Collection<User> getUsers() {
		return this.users;
	}

	public void logicalRemoveUser(User anUser) {
		anUser.setRemoved(true);
	}
	
	//usado solo por los tests para dejar la base como estaba
	@Deprecated
	public void removeUser(User anUser) throws UnknownUserException {
		User anUserToRemove = null;
		for(User user: this.users){
			if(user.equals(anUser)){
				anUserToRemove = user;
				break;
			}
		}
		if(anUserToRemove != null){
			this.users.remove(anUserToRemove);
		}else{
			throw new UnknownUserException();
		}
	}
}
