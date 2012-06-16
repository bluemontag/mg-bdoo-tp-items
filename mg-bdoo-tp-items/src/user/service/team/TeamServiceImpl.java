package user.service.team;

import itemTracker.domain.ItemTracker;

import java.util.Collection;

import user.domain.User;
import user.domain.team.Team;
import user.dto.UserDTOForLists;
import user.dto.team.TeamDTO;
import user.dto.team.TeamDTOFactory;
import user.exception.UnknownUserException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamServiceImpl extends AbstractServiceImpl implements TeamServiceBI {

	@Override
	public TeamDTO createTeam(String sessionToken, String aTeamName, Collection<UserDTOForLists> usersDTOs)
			throws UnknownUserException, TeamAlreadyExistsException {

		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(usersDTOs);
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

	@Override
	public TeamDTO getTeam(String sessionToken, TeamDTO aTeamDTO) throws UnknownTeamException {
		Team ateam = this.getTeamRespository().getTeamByDTO(aTeamDTO);
		return (TeamDTO) TeamDTOFactory.getInstance().getDTO(ateam);
	}
}
