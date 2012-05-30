/**
 * 
 */
package proyect.service;

import itemTracker.domain.ItemTracker;

import java.util.Collection;

import proyect.domain.Proyect;
import proyect.dto.ProyectDTO;
import proyect.dto.ProyectDTOFactory;
import proyect.exception.ProyectAlreadyExistsException;
import proyect.exception.UnknownProyectException;
import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectServiceImpl extends AbstractServiceImpl implements ProyectServiceBI {

	@Override
	public ProyectDTO createProyect(String aProyectName, UserDTO aProyectLeaderUserDTO)
			throws ProyectAlreadyExistsException, UnknownUserException {

		try {
			this.getProyectRespository().getProyectByName(aProyectName);

		} catch (UnknownProyectException unknownProyectException) {
			ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();

			User aProyectLeaderUser = this.getUserRespository().getUserByDTO(aProyectLeaderUserDTO);
			Proyect aProyect = new Proyect(aProyectName, aProyectLeaderUser);
			theItemTracker.addProyect(aProyect);

			ProyectDTO proyectDTO = (ProyectDTO) ProyectDTOFactory.getInstance().getDTO(aProyect);
			return proyectDTO;
		}
		throw new ProyectAlreadyExistsException("El proyecto " + aProyectName + " ya existe.");
	}

	@Override
	public ProyectDTO getProyect(ProyectDTO aProyectDTO) throws UnknownProyectException {
		Proyect aProyect = this.getProyectRespository().getProyectByDTO(aProyectDTO);
		return (ProyectDTO) ProyectDTOFactory.getInstance().getDTO(aProyect);
	}

	@Override
	public void addUsersToProyect(ProyectDTO aProyectDTO, Collection<UserDTOForLists> usersDTOs)
			throws UnknownProyectException, UnknownUserException, DTOConcurrencyException {

		Proyect aProyect = this.getProyectRespository().getProyectByDTO(aProyectDTO);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(usersDTOs);
		this.checkDTOConcurrency(aProyectDTO, aProyect);
		aProyect.addUsers(users);
	}

	@Override
	public void removeProyect(ProyectDTO aProyectDTOToRemove) throws UnknownProyectException, DTOConcurrencyException {

		ItemTracker theItemTracker = this.getItemTrackerRespository().getItemTracker();
		Proyect aProyectToRemove = this.getProyectRespository().getProyectByDTO(aProyectDTOToRemove);
		this.checkDTOConcurrency(aProyectDTOToRemove, aProyectToRemove);
		theItemTracker.removeProyect(aProyectToRemove);
	}

	@Override
	public void updateProyect(ProyectDTO aProyectDTOToUpdate) throws UnknownProyectException, DTOConcurrencyException,
			UnknownUserException {

		Proyect aProyectToUpdate = this.getProyectRespository().getProyectByDTO(aProyectDTOToUpdate);
		this.checkDTOConcurrency(aProyectDTOToUpdate, aProyectToUpdate);
		Collection<User> users = this.getUserRespository().getUsersByDTOsList(aProyectDTOToUpdate.getUsers());
		User userPoyectLeader = this.getUserRespository().getUserByDTO(aProyectDTOToUpdate.getLeader());
		aProyectToUpdate.setUsers(users);
		aProyectToUpdate.setName(aProyectDTOToUpdate.getName());
		aProyectToUpdate.setLeader(userPoyectLeader);

	}
}
