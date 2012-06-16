package itemTracker.domain;

import java.util.Collection;
import java.util.HashSet;

import project.domain.Project;
import project.exception.UnknownProjectException;
import user.domain.User;
import user.domain.team.Team;
import user.exception.UnknownUserException;
import user.exception.team.UnknownTeamException;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTracker extends BaseDomain {

	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";

	private Collection<User> users = new HashSet<User>();
	private User adminUser = null;
	private Collection<Project> projects = new HashSet<Project>();
	private Collection<Team> teams = new HashSet<Team>();

	public ItemTracker() {
		this.users = new HashSet<User>();
		this.setProjects(new HashSet<Project>());
	}

	public void addUser(User anUser) {
		this.users.add(anUser);
	}

	@Deprecated
	// solo se usa por hibernate
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<User> getUsers() {
		return this.users;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User theAdminUser) {
		this.adminUser = theAdminUser;
	}

	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void addProject(Project aProject) {
		this.projects.add(aProject);
	}

	public void logicalRemoveUser(User anUser) {
		anUser.setRemoved(true);
	}

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	public void removeUser(User anUser) throws UnknownUserException {
		boolean removed = this.users.remove(anUser);
		if (!removed) {
			throw new UnknownUserException();
		}
	}

	public void logicalRemoveProject(Project aProject) {
		aProject.setRemoved(true);
	}

	// Los projectos tiene eliminacion fisica.
	public void removeProject(Project aProject) throws UnknownProjectException {

		boolean removed = this.projects.remove(aProject);
		if (!removed) {
			throw new UnknownProjectException("El projecto que desea eliminar no existe.");
		}
	}

	@Deprecated
	// solo se usa por hibernate
	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	public Collection<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team aTeam) {
		this.teams.add(aTeam);
	}

	public void removeTeam(Team aTeam) throws UnknownTeamException {
		boolean removed = this.teams.remove(aTeam);
		if (!removed) {
			throw new UnknownTeamException("El equipo que desea eliminar no existe.");
		}
	}
}
