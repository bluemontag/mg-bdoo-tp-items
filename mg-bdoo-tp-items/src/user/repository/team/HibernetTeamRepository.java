package user.repository.team;

import user.domain.team.Team;
import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;
import base.repository.HibernateBaseRepository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class HibernetTeamRepository extends HibernateBaseRepository implements TeamRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Team.class;
	}

	@Override
	public Team getTeamByOid(String anId) throws UnknownTeamException {
		Team aTeam = (Team) this.findeByOid(this.getEntityClass(), anId);
		if (aTeam == null) {
			throw new UnknownTeamException("El proyecto no existe.");
		}
		return aTeam;
	}

	@Override
	public Team getTeamByDTO(TeamDTO aTeamDTOToRemove) throws UnknownTeamException {
		return this.getTeamByOid(aTeamDTOToRemove.getOid());
	}
}
