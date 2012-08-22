package project.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import project.domain.Project;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import user.dto.UserDTOForLists;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectDTO extends AbstractDTO {

	private String name;
	private final Date creationDate;
	private UserDTO leader;
	private Collection<UserDTOForLists> users = new HashSet<UserDTOForLists>();

	@SuppressWarnings("unchecked")
	public ProjectDTO(Project aProject) {
		super(aProject);
		this.name = aProject.getName();
		this.creationDate = aProject.getCreationDate();
		this.leader = (UserDTO) UserDTOFactory.getInstance().getDTO(aProject.getLeader());
		this.users = (Collection<UserDTOForLists>) UserDTOFactory.getInstance().getDTOList(aProject.getUsers());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDTO getLeader() {
		return leader;
	}

	public void setLeader(UserDTO leader) {
		this.leader = leader;
	}

	public Collection<UserDTOForLists> getUsers() {
		return users;
	}

	public void setUsersFromDTOsForList(Collection<UserDTOForLists> usersDTOForList) {
		this.users = usersDTOForList;
	}

	@SuppressWarnings("unchecked")
	public void setUsers(Collection<UserDTO> usersDTO) {
		this.users = (Collection<UserDTOForLists>) UserDTOFactory.getInstance().getDTOsForListFromDTOs(usersDTO);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void removeAllUsers() {
		this.users = new HashSet<UserDTOForLists>();
	}
}
