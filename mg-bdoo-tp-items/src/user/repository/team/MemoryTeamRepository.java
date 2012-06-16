package user.repository.team;

import user.domain.team.Team;
import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class MemoryTeamRepository implements TeamRepositoryBI {

	@Override
	public Team getTeamByOid(String anId) throws UnknownTeamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamByDTO(TeamDTO aTeamDTOToRemove) throws UnknownTeamException {
		return this.getTeamByOid(aTeamDTOToRemove.getOid());
	}

}
