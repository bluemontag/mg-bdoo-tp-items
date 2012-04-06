package base.service;

import user.repository.UserRepositoryBI;
import itemTracker.repository.ItemTrackerRepositoryBI;
import base.repository.AbstractRepositoryFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class AbstractServiceImpl {
	
	private AbstractRepositoryFinder repositoryFinder;

	// RepositoryFinder
	public AbstractRepositoryFinder getRepositoryFinder(){
		return repositoryFinder;
	}
	
	public void setRepositoryFinder(AbstractRepositoryFinder repositoryFinder){
		this.repositoryFinder = repositoryFinder;
	}
	
	// Repositorios
	public ItemTrackerRepositoryBI getItemTrackerRespository() {
		return this.getRepositoryFinder().getItemTrackerRepository();
	}
	
	public UserRepositoryBI getUserRespository() {
		return this.getRepositoryFinder().getUserRepository();
	}	
}


