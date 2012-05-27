package proyect.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import proyect.domain.Proyect;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import user.dto.UserDTOForLists;
import base.dto.AbstractDTO;

public class ProyectDTO extends AbstractDTO {

	private String name;
	private final Date creationDate;
	private UserDTO leader;
	private Collection<UserDTOForLists> users = new HashSet<UserDTOForLists>();

	@SuppressWarnings("unchecked")
	public ProyectDTO(Proyect aProyect) {
		super(aProyect);
		this.name = aProyect.getName();
		this.creationDate = aProyect.getCreationDate();
		this.leader = (UserDTO) UserDTOFactory.getInstance().getDTO(aProyect.getLeader());
		this.users = (Collection<UserDTOForLists>) UserDTOFactory.getInstance().getDTOList(aProyect.getUsers());
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

	public void setUsers(Collection<UserDTOForLists> users) {
		this.users = users;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
