package base.service;

import user.repository.UserRepositoryBI;
import itemTracker.repository.ItemTrackerRepositoryBI;
import base.dto.DTOFactoryFinder;
import base.repository.AbstractRepositoryFinder;


public class AbstractServiceImpl {
	
	private AbstractRepositoryFinder repositoryFinder;
	private DTOFactoryFinder dtoFactoryFinder;

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


