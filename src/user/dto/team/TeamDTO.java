package user.dto.team;

import java.util.Collection;
import java.util.HashSet;

import user.domain.team.Team;
import user.dto.UserDTOFactory;
import user.dto.UserDTOForLists;
import base.dto.AbstractDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamDTO extends AbstractDTO {

	private String name;
	private final Collection<UserDTOForLists> users = new HashSet<UserDTOForLists>();

	@SuppressWarnings("unchecked")
	public TeamDTO(Team aTeam) {
		super(aTeam);
		this.setName(aTeam.getName());
		this.users.addAll((Collection<UserDTOForLists>) UserDTOFactory.getInstance().getDTOList(aTeam.getUsers()));
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Id: " + this.getOid() + " - Team Name: " + this.getName();
	}
}
