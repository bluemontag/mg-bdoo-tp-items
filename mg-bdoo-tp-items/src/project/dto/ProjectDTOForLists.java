package project.dto;

import java.util.Date;

import project.domain.Project;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import base.dto.AbstractDTOForLists;

public class ProjectDTOForLists extends AbstractDTOForLists {

	private final String name;
	private final Date creationDate;
	private final UserDTO leader;

	public String getName() {
		return name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public UserDTO getLeader() {
		return leader;
	}

	public ProjectDTOForLists(Project aProject) {
		super(aProject);
		this.name = aProject.getName();
		this.creationDate = aProject.getCreationDate();
		this.leader = (UserDTO) UserDTOFactory.getInstance().getDTO(aProject.getLeader());
	}

	public ProjectDTOForLists(ProjectDTO aProjectDTO) {
		super(aProjectDTO);
		this.name = aProjectDTO.getName();
		this.creationDate = aProjectDTO.getCreationDate();
		this.leader = aProjectDTO.getLeader();
	}
}
