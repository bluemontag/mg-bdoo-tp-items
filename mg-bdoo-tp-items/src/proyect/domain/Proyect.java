package proyect.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import user.domain.User;
import user.exception.UnknownUserException;

import base.domain.BaseDomain;

public class Proyect extends BaseDomain{
	
	private String name;
	private Date creationDate;
	private User leader;
	private Collection<User> users = new HashSet<User>();

	public Proyect() {
		// Si no esta este contructor, hibernate no funciona.
	}
	
	public Proyect(String aProyectName, User aProyectLeader) {
		this.name = aProyectName;
		this.leader = aProyectLeader;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	public User getLeader() {
		return leader;
	}
	public void addUser(User aUser) {
		this.users.add(aUser);
	}
	
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	public Collection<User> getUsers() {
		return this.users;
	}
	
	public void removeUser(User anUser) throws UnknownUserException {
		boolean removed = this.users.remove(anUser);
		if(!removed){
			throw new UnknownUserException();
		}
	}

	public void addUsers(Collection<User> users) {
		this.users.addAll(users);
	}
}
