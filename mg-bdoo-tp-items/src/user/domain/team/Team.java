package user.domain.team;

import java.util.Collection;
import java.util.HashSet;

import user.domain.User;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class Team extends BaseDomain {

	private String name;
	private final Collection<User> users = new HashSet<User>();

	public Team() {
	}

	public Team(String aName, Collection<User> users) {
		this.name = aName;
		this.users.addAll(users);
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
}
