package user.service.team;

import itemTracker.domain.ItemTracker;

import java.util.Collection;

import user.domain.User;
import user.domain.team.Team;
import user.dto.UserDTO;
import user.dto.team.TeamDTO;
import user.dto.team.TeamDTOFactory;
import user.exception.UnknownUserException;
import user.exception.team.UnknownTeamException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamServiceImpl extends AbstractServiceImpl implements TeamServiceBI {

	@Override
	public TeamDTO createTeam(String sessionToken, String aTeamName, Collection<UserDTO> usersDTOs)
			throws UnknownUserException {

		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Collection<User> users = this.getUserRespository().getUsersByDTOs(usersDTOs);
		Team newTeam = new Team(aTeamName, users);
		theItemTracker.addTeam(newTeam);
		return (TeamDTO) TeamDTOFactory.getInstance().getDTO(newTeam);
	}

	@Override
	public void removeTeam(String sessionToken, TeamDTO aTeamDTOToRemove) throws DTOConcurrencyException,
			UnknownTeamException {
		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Team aTeam = this.getTeamRespository().getTeamByDTO(aTeamDTOToRemove);
		this.checkDTOConcurrency(aTeamDTOToRemove, aTeam);
		theItemTracker.removeTeam(aTeam);

	}
}
