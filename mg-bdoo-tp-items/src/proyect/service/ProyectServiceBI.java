package proyect.service;

import java.util.Collection;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;
import proyect.exception.UnknownProyectException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ProyectServiceBI {

	// Creats
	
	ProyectDTO createProyect(String aProyectName, UserDTO aProyectLeaderUserDTO) throws ProyectAlreadyExistsException, UnknownUserException;
	
	// Lists
	
	// Retrives

	ProyectDTO getProyect(ProyectDTO anCreatedProyectDTO) throws UnknownProyectException;
	
	// Updates
	
	void addUsersToProyect(ProyectDTO aProyectDTO, Collection<UserDTOForLists> users) throws UnknownProyectException, UnknownUserException;
	
	// Removes
	
	void removeProyect(ProyectDTO anProyectDTOToRemove) throws UnknownProyectException;
}
