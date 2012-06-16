/**
 * 
 */
package project.service;

import itemTracker.domain.ItemTracker;

import java.util.Collection;

import project.domain.Project;
import project.dto.ProjectDTO;
import project.dto.ProjectDTOFactory;
import project.exception.ProjectAlreadyExistsException;
import project.exception.UnknownProjectException;
import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectServiceImpl extends AbstractServiceImpl implements ProjectServiceBI {

	@Override
	public ProjectDTO createProject(String sessionToken, String aProjectName, UserDTO aProjectLeaderUserDTO)
			throws ProjectAlreadyExistsException, UnknownUserException {

		try {
			this.getProjectRespository().getProjectByName(aProjectName);

		} catch (UnknownProjectException unknownProjectException) {
			ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();

			User aProjectLeaderUser = this.getUserRespository().getUserByDTO(aProjectLeaderUserDTO);
			Project aProject = new Project(aProjectName, aProjectLeaderUser);
			theItemTracker.addProject(aProject);

			ProjectDTO projectDTO = (ProjectDTO) ProjectDTOFactory.getInstance().getDTO(aProject);
			return projectDTO;
		}
		throw new ProjectAlreadyExistsException("El proyecto " + aProjectName + " ya existe.");
	}

	@Override
	public ProjectDTO getProject(String sessionToken, ProjectDTO aProjectDTO) throws UnknownProjectException {
		Project aProject = this.getProjectRespository().getProjectByDTO(aProjectDTO);
		return (ProjectDTO) ProjectDTOFactory.getInstance().getDTO(aProject);
	}

	@Override
	public void addUsersToProject(String sessionToken, ProjectDTO aProjectDTO, Collection<UserDTOForLists> usersDTOs)
			throws UnknownProjectException, UnknownUserException, DTOConcurrencyException {

		Project aProject = this.getProjectRespository().getProjectByDTO(aProjectDTO);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(usersDTOs);
		this.checkDTOConcurrency(aProjectDTO, aProject);
		aProject.addUsers(users);
	}

	@Override
	public void removeProject(String sessionToken, ProjectDTO aProjectDTOToRemove) throws UnknownProjectException,
			DTOConcurrencyException {

		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Project aProjectToRemove = this.getProjectRespository().getProjectByDTO(aProjectDTOToRemove);
		this.checkDTOConcurrency(aProjectDTOToRemove, aProjectToRemove);
		theItemTracker.removeProject(aProjectToRemove);
	}

	@Override
	public void updateProject(String sessionToken, ProjectDTO aProjectDTOToUpdate) throws UnknownProjectException,
			DTOConcurrencyException, UnknownUserException {

		Project aProjectToUpdate = this.getProjectRespository().getProjectByDTO(aProjectDTOToUpdate);
		this.checkDTOConcurrency(aProjectDTOToUpdate, aProjectToUpdate);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(aProjectDTOToUpdate.getUsers());
		User userPojectLeader = this.getUserRespository().getUserByDTO(aProjectDTOToUpdate.getLeader());
		aProjectToUpdate.updateUsers(users);
		aProjectToUpdate.setName(aProjectDTOToUpdate.getName());
		aProjectToUpdate.setLeader(userPojectLeader);

	}
}
