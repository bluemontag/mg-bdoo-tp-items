package base.service;

import item.repository.ItemRepositoryBI;
import item.repository.itemType.ItemTypeRepositoryBI;
import itemTracker.repository.ItemTrackerRepositoryBI;
import project.repository.ProjectRepositoryBI;
import user.repository.UserRepositoryBI;
import user.repository.team.TeamRepositoryBI;
import workflow.repository.WorkflowRepositoryBI;
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

	public ProjectRepositoryBI getProjectRespository() {
		return this.getRepositoryFinder().getProjectRepository();
	}

	public TeamRepositoryBI getTeamRespository() {
		return this.getRepositoryFinder().getTeamRepository();
	}

	public ItemRepositoryBI getItemRepository() {
		return this.getRepositoryFinder().getItemRepository();
	}
	
	public ItemTypeRepositoryBI getItemTypeRepository() {
		return this.getRepositoryFinder().getItemTypeRepository();
	}
	
	public WorkflowRepositoryBI getWorkflowRespository() {
		return this.getRepositoryFinder().getWorkflowRepository();
	}

	// Chequeo de concurrencia.
	final protected void checkDTOConcurrency(AbstractDTO aDTO, BaseDomain aBaseDomianObject)
			throws DTOConcurrencyException {
		if (!aDTO.getVersion().equals(aBaseDomianObject.getVersion())) {
			throw new DTOConcurrencyException("La entidad que desea modificar ha sido usada por otro usuario.");
		}
	}
}
