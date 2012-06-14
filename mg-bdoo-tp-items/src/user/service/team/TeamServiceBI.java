package user.service.team;

import java.util.Collection;

import user.dto.UserDTOForLists;
import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface TeamServiceBI {

	// Creats

	TeamDTO createTeam(String sessionToken, String aTeamName, Collection<UserDTOForLists> usersDTOs)
			throws UnknownUserException, TeamAlreadyExistsException;

	// Lists

	// Retrives

	// Updates

	// Removes

	void removeTeam(String sessionToken, TeamDTO aTeamDTOToRemove) throws DTOConcurrencyException, UnknownTeamException;

	// usado por los tests para dejar la base como estaba
	// @Deprecated
}
