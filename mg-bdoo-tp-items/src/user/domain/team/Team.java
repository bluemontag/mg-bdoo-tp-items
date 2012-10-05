package user.domain.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import user.domain.User;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class Team extends BaseDomain {

	private String name;
	private final Collection<User> users;

	public Team() {
		this.users = new ArrayList<User>();
	}

	public Team(String aName, Collection<User> users) {
		this.name = aName;
		this.users = new ArrayList<User>();
		this.users.addAll(users);
		/*
		 * la relacion es bidireccional, por lo tanto tengo que decirles a los
		 * usuarios que estan en este team tambien
		 */
		Iterator<User> i = this.users.iterator();
		while (i.hasNext()) {
			User u = i.next();
			u.addTeam(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	protected void setUsers(Collection<User> users) {
		this.users.addAll(users);
	}

	public void addUser(User anUser) {
		this.users.add(anUser);
	}

	public void removeUsers() {
		for (User user : this.users) {
			user.removeFromTeam(this);
		}
	}
}
