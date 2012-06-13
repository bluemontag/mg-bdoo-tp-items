package user.repository.team;

import user.domain.team.Team;
import base.repository.HibernateBaseRepository;

public class HibernetTeamRepository extends HibernateBaseRepository implements TeamRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return Team.class;
	}
}
