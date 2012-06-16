package user.repository.team;

import user.domain.team.Team;
import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface TeamRepositoryBI {

	Team getTeamByOid(String anId) throws UnknownTeamException;

	Team getTeamByDTO(TeamDTO aTeamDTOToRemove) throws UnknownTeamException;

}
