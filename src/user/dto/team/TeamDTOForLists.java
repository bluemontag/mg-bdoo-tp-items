package user.dto.team;

import user.domain.team.Team;
import base.dto.AbstractDTOForLists;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamDTOForLists extends AbstractDTOForLists {

	private String name;

	public TeamDTOForLists(Team aTeam) {
		super(aTeam);
		this.name = aTeam.getName();
	}

	public TeamDTOForLists(TeamDTO aTeamDTO) {
		super(aTeamDTO);
		this.name = aTeamDTO.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		this.name = aName;
	}

	@Override
	public String toString() {
		return "Id: " + this.getOid() + " - Team Name: " + this.getName();
	}
}
