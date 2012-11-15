package user.dto.team;

import user.domain.team.Team;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.dto.AbstractDTOForLists;
import base.dto.DTOFactory;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamDTOFactory extends DTOFactory {

	private TeamDTOFactory() {
	}

	public static DTOFactory getInstance() {
		return new TeamDTOFactory();
	}

	@Override
	public AbstractDTO getDTO(BaseDomain aTeam) {
		return new TeamDTO((Team) aTeam);
	}

	@Override
	public AbstractDTOForLists getDTOForLists(BaseDomain aBaseDomainObject) {
		return new TeamDTOForLists((Team) aBaseDomainObject);
	}

	@Override
	protected AbstractDTOForLists getDTOForListInstance(AbstractDTO aTeamDTO) {
		return new TeamDTOForLists((TeamDTO) aTeamDTO);
	}

}
