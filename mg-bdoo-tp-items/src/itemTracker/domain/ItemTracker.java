package itemTracker.domain;

import item.domain.Item;
import item.domain.itemType.ItemType;
import item.exception.UnknownItemException;

import java.util.ArrayList;
import java.util.Collection;

import project.domain.Project;
import project.exception.UnknownProjectException;
import user.domain.User;
import user.domain.team.Team;
import user.exception.UnknownUserException;
import user.exception.team.UnknownTeamException;
import workflow.domain.Workflow;
import workflow.dto.WorkflowDTO;
import workflow.exception.UnknownWorkflowException;
import base.domain.BaseDomain;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTracker extends BaseDomain {

	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";

	private Collection<User> users = new ArrayList<User>();
	private User adminUser = null;
	private Collection<Project> projects;
	private Collection<Team> teams;
	private Collection<ItemType> itemTypes;
	private Collection<Item> items;
	private Collection<Workflow> workflows;
	private long lastItemNum;

	public ItemTracker() {
		this.users = new ArrayList<User>();
		this.projects = new ArrayList<Project>();
		this.items = new ArrayList<Item>();
		this.itemTypes = new ArrayList<ItemType>();
		this.teams = new ArrayList<Team>();
		this.workflows = new ArrayList<Workflow>();
	}

	public void addUser(User anUser) {
		this.users.add(anUser);
	}

	@Deprecated
	// solo se usa por hibernate
	protected void setUsers(Collection<User> users) {
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

	protected void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	public Collection<Project> getProjects() {
		return projects;
	}

	public void addProject(Project aProject) {
		this.projects.add(aProject);
	}

	public void addWorkflow(Workflow wf) {
		this.workflows.add(wf);
	}

	public void setLastItemNum(long lastItemNum) {
		this.lastItemNum = lastItemNum;
	}

	public long getLastItemNum() {
		return lastItemNum;
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

	// usado solo por los tests para dejar la base como estaba
	@Deprecated
	public void removeItem(Item item) throws UnknownItemException {
		item.historyEmpty();
		boolean removed = this.items.remove(item);
		if (!removed) {
			throw new UnknownItemException();
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

	public void addItem(Item i) {
		this.items.add(i);
	}

	public Collection<ItemType> getItemTypes() {
		return itemTypes;
	}

	protected void setItemTypes(Collection<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public void addItemType(ItemType it) {
		this.itemTypes.add(it);
	}

	public void removeItemType(ItemType it) {
		this.itemTypes.remove(it);
	}

	public Collection<Item> getItems() {
		return items;
	}

	protected void setItems(Collection<Item> items) {
		this.items = items;
	}

	@Deprecated
	// solo se usa por hibernate
	protected void setTeams(Collection<Team> teams) {
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

	public Collection<Workflow> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(Collection<Workflow> workflows) {
		this.workflows = workflows;
	}

	public void logicalRemoveWorkflow(Workflow w) {
		w.setRemoved(true);
	}

	@Deprecated
	public void removeWorkflow(Workflow aWorkflow) throws UnknownWorkflowException {
		boolean removed = this.workflows.remove(aWorkflow);
		if (!removed)
			throw new UnknownWorkflowException("El workflow que desea eliminar no existe");
	}

	public Workflow findWorkflowByOid(WorkflowDTO aWorkflowDTO) {
		for (Workflow aWorkflow : this.getWorkflows()) {
			if (aWorkflow.equalsToDTO(aWorkflowDTO)) {
				return aWorkflow;
			}
		}
		return null;
	}

	public long getNextItemNum() {
		long nextItemNum = this.lastItemNum;
		this.lastItemNum++;
		return nextItemNum;
	}
}