package project.domain;

import item.domain.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import user.domain.User;
import user.exception.UnknownUserException;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class Project extends BaseDomain {

	private String name;
	private Date creationDate;
	private User leader;
	private Collection<User> users;
	private Collection<Item> items;

	public Project() {
		this.users = new ArrayList<User>();
		this.items = new ArrayList<Item>();
	}

	public Project(String aProjectName, User aProjectLeader) {
		this.generarOid();
		this.users = new ArrayList<User>();
		this.items = new ArrayList<Item>();
		this.name = aProjectName;
		this.leader = aProjectLeader;
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
		if (!removed) {
			throw new UnknownUserException();
		}
	}

	public void addUsers(Collection<User> users) {
		this.users.addAll(users);
	}

	public void updateUsers(Collection<User> users) {
		this.users.clear();
		this.users.addAll(users);
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
}
