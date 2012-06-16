package project.service;

import java.util.Collection;

import project.dto.ProjectDTO;
import project.exception.ProjectAlreadyExistsException;
import project.exception.UnknownProjectException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ProyectServiceBI {

	// Creats

	ProjectDTO createProyect(String sessionToken, String aProyectName, UserDTO aProyectLeaderUserDTO)
			throws ProjectAlreadyExistsException, UnknownUserException;

	// Lists

	// Retrives

	ProjectDTO getProyect(String sessionToken, ProjectDTO aProyectDTO) throws UnknownProjectException;

	// Updates

	void addUsersToProyect(String sessionToken, ProjectDTO aProyectDTO, Collection<UserDTOForLists> users)
			throws UnknownProjectException, UnknownUserException, DTOConcurrencyException;

	void updateProyect(String sessionToken, ProjectDTO aCreatedProyectDTO) throws UnknownProjectException,
			DTOConcurrencyException, UnknownUserException;

	// Removes

	void removeProyect(String sessionToken, ProjectDTO anProyectDTOToRemove) throws UnknownProjectException,
			DTOConcurrencyException;
}
