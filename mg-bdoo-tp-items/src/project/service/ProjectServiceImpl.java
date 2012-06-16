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
public class ProjectServiceImpl extends AbstractServiceImpl implements ProyectServiceBI {

	@Override
	public ProjectDTO createProyect(String sessionToken, String aProyectName, UserDTO aProyectLeaderUserDTO)
			throws ProjectAlreadyExistsException, UnknownUserException {

		try {
			this.getProyectRespository().getProyectByName(aProyectName);

		} catch (UnknownProjectException unknownProyectException) {
			ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();

			User aProyectLeaderUser = this.getUserRespository().getUserByDTO(aProyectLeaderUserDTO);
			Project aProject = new Project(aProyectName, aProyectLeaderUser);
			theItemTracker.addProyect(aProject);

			ProjectDTO proyectDTO = (ProjectDTO) ProjectDTOFactory.getInstance().getDTO(aProject);
			return proyectDTO;
		}
		throw new ProjectAlreadyExistsException("El proyecto " + aProyectName + " ya existe.");
	}

	@Override
	public ProjectDTO getProyect(String sessionToken, ProjectDTO aProyectDTO) throws UnknownProjectException {
		Project aProyect = this.getProyectRespository().getProyectByDTO(aProyectDTO);
		return (ProjectDTO) ProjectDTOFactory.getInstance().getDTO(aProyect);
	}

	@Override
	public void addUsersToProyect(String sessionToken, ProjectDTO aProyectDTO, Collection<UserDTOForLists> usersDTOs)
			throws UnknownProjectException, UnknownUserException, DTOConcurrencyException {

		Project aProyect = this.getProyectRespository().getProyectByDTO(aProyectDTO);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(usersDTOs);
		this.checkDTOConcurrency(aProyectDTO, aProyect);
		aProyect.addUsers(users);
	}

	@Override
	public void removeProyect(String sessionToken, ProjectDTO aProyectDTOToRemove) throws UnknownProjectException,
			DTOConcurrencyException {

		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Project aProyectToRemove = this.getProyectRespository().getProyectByDTO(aProyectDTOToRemove);
		this.checkDTOConcurrency(aProyectDTOToRemove, aProyectToRemove);
		theItemTracker.removeProyect(aProyectToRemove);
	}

	@Override
	public void updateProyect(String sessionToken, ProjectDTO aProyectDTOToUpdate) throws UnknownProjectException,
			DTOConcurrencyException, UnknownUserException {

		Project aProyectToUpdate = this.getProyectRespository().getProyectByDTO(aProyectDTOToUpdate);
		this.checkDTOConcurrency(aProyectDTOToUpdate, aProyectToUpdate);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(aProyectDTOToUpdate.getUsers());
		User userPoyectLeader = this.getUserRespository().getUserByDTO(aProyectDTOToUpdate.getLeader());
		aProyectToUpdate.updateUsers(users);
		aProyectToUpdate.setName(aProyectDTOToUpdate.getName());
		aProyectToUpdate.setLeader(userPoyectLeader);

	}
}
