package user.domain;

import java.util.ArrayList;
import java.util.Collection;

import user.domain.team.Team;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class User extends BaseDomain {

	protected String userName;
	protected String password;
	protected Collection<Team> teams;

	public User() {
		this.teams = new ArrayList<Team>();
	}

	public User(String anUserName, String aPassword) {
		this.teams = new ArrayList<Team>();
		this.userName = anUserName;
		this.password = aPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String anUserName) {
		this.userName = anUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String aPassword) {
		this.password = aPassword;
	}

	public Collection<Team> getTeams() {
		return teams;
	}

	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team t) {
		// agrego siempre que no este ya.
		if (!this.teams.contains(t))
			this.teams.add(t);
	}

	public void removeFromTeam(Team aTeam) {
		this.teams.remove(aTeam);
	}
}
