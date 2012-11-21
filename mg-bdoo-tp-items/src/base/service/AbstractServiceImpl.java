package base.service;

import item.repository.ItemRepositoryBI;
import item.repository.itemType.ItemTypeRepositoryBI;
import itemTracker.repository.ItemTrackerRepositoryBI;
import project.repository.ProjectRepositoryBI;
import user.domain.User;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.repository.UserRepositoryBI;
import user.repository.team.TeamRepositoryBI;
import workflow.repository.WorkflowRepositoryBI;
import workflow.repository.state.ItemStateRepositoryBI;
import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.exception.DTOConcurrencyException;
import base.exception.UserNotLoggedException;
import base.repository.AbstractRepositoryFinder;
import base.security.ItemTrackerSession;

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
	public ItemTrackerRepositoryBI getItemTrackerRepository() {
		return this.getRepositoryFinder().getItemTrackerRepository();
	}

	public UserRepositoryBI getUserRepository() {
		return this.getRepositoryFinder().getUserRepository();
	}

	public ProjectRepositoryBI getProjectRepository() {
		return this.getRepositoryFinder().getProjectRepository();
	}

	public TeamRepositoryBI getTeamRepository() {
		return this.getRepositoryFinder().getTeamRepository();
	}

	public ItemRepositoryBI getItemRepository() {
		return this.getRepositoryFinder().getItemRepository();
	}

	public ItemTypeRepositoryBI getItemTypeRepository() {
		return this.getRepositoryFinder().getItemTypeRepository();
	}

	public WorkflowRepositoryBI getWorkflowRepository() {
		return this.getRepositoryFinder().getWorkflowRepository();
	}

	public ItemStateRepositoryBI getItemStateRepository() {
		return this.getRepositoryFinder().getItemStateRepository();
	}

	// Chequeo de concurrencia.
	final protected void checkDTOConcurrency(AbstractDTO aDTO, BaseDomain aBaseDomianObject)
			throws DTOConcurrencyException {
		if (!aDTO.getVersion().equals(aBaseDomianObject.getVersion())) {
			throw new DTOConcurrencyException("La entidad que desea modificar ha sido usada por otro usuario.");
		}
	}

	protected User getCurrentUser(String sessionToken) throws UserNotLoggedException {
		UserDTO anUserDTO = ItemTrackerSession.getInstance().getUserDTOLogged(sessionToken);
		User anUser = null;
		try {
			anUser = this.getUserRepository().getUserByDTO(anUserDTO);
		} catch (UnknownUserException e) {
			throw new UserNotLoggedException("Si pasa esto.. cagamos!");
		}
		return anUser;
	}
}
