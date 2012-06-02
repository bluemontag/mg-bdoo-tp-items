package proyect.service;

import java.util.Collection;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;
import proyect.exception.UnknownProyectException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ProyectServiceBI {

	// Creats

	ProyectDTO createProyect(String aProyectName, UserDTO aProyectLeaderUserDTO) throws ProyectAlreadyExistsException,
			UnknownUserException;

	// Lists

	// Retrives

	ProyectDTO getProyect(ProyectDTO aProyectDTO) throws UnknownProyectException;

	// Updates

	void addUsersToProyect(ProyectDTO aProyectDTO, Collection<UserDTOForLists> users) throws UnknownProyectException,
			UnknownUserException, DTOConcurrencyException;

	void updateProyect(ProyectDTO aCreatedProyectDTO) throws UnknownProyectException, DTOConcurrencyException,
			UnknownUserException;

	// Removes

	void removeProyect(ProyectDTO anProyectDTOToRemove) throws UnknownProyectException, DTOConcurrencyException;
}
