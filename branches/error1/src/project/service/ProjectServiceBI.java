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
public interface ProjectServiceBI {

	// Creats

	ProjectDTO createProject(String sessionToken, String aProjectName, UserDTO aProjectLeaderUserDTO)
			throws ProjectAlreadyExistsException, UnknownUserException;

	// Lists

	// Retrives

	ProjectDTO getProject(String sessionToken, ProjectDTO aProjectDTO) throws UnknownProjectException;

	// Updates

	void addUsersToProject(String sessionToken, ProjectDTO aProjectDTO, Collection<UserDTOForLists> users)
			throws UnknownProjectException, UnknownUserException, DTOConcurrencyException;

	void updateProject(String sessionToken, ProjectDTO aCreatedProjectDTO) throws UnknownProjectException,
			DTOConcurrencyException, UnknownUserException;

	// Removes

	void removeProject(String sessionToken, ProjectDTO anProjectDTOToRemove) throws UnknownProjectException,
			DTOConcurrencyException;
}
