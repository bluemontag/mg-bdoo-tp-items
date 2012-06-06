package base.service;

import itemTracker.repository.ItemTrackerRepositoryBI;
import proyect.repository.ProyectRepositoryBI;
import user.repository.UserRepositoryBI;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.exception.DTOConcurrencyException;
import base.repository.AbstractRepositoryFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class AbstractServiceImpl {

	private AbstractRepositoryFinder repositoryFinder;

	// RepositoryFinder
	public AbstractRepositoryFinder getRepositoryFinder() {
		return repositoryFinder;
	}

	public void setRepositoryFinder(AbstractRepositoryFinder repositoryFinder) {
		this.repositoryFinder = repositoryFinder;
	}

	// Repositorios
	public ItemTrackerRepositoryBI getItemTrackerRespository() {
		return this.getRepositoryFinder().getItemTrackerRepository();
	}

	public UserRepositoryBI getUserRespository() {
		return this.getRepositoryFinder().getUserRepository();
	}

	public ProyectRepositoryBI getProyectRespository() {
		return this.getRepositoryFinder().getProyectRepository();
	}

	// Chequeo de concurrencia.
	final protected void checkDTOConcurrency(AbstractDTO aDTO, BaseDomain aBaseDomianObject)
			throws DTOConcurrencyException {
		if (!aDTO.getVersion().equals(aBaseDomianObject.getVersion())) {
			throw new DTOConcurrencyException("La entidad que desea modificar ha sido usada por otro usuario.");
		}
	}
}
