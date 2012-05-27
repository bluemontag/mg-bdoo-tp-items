package proyect.service;

import java.util.Collection;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ProyectServiceBI {

	
	ProyectDTO createProyect(String aProyectName, UserDTO aProyectLeaderUserDTO) throws ProyectAlreadyExistsException, UnknownUserException;

	void addUsersToProyect(ProyectDTO aProyectDTO, Collection<UserDTOForLists> users);
}
