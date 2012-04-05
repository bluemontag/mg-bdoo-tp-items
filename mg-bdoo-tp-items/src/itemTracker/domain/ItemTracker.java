package itemTracker.domain;


import java.awt.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import base.domain.BaseDomain;

import user.domain.User;

//import itemTracker.exception.UnknownUserException;


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

	public void removeUser(User anUser) {
		anUser.setRemoved(true);
	}
}
