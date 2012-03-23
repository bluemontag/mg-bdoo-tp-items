package base.service;

import itemTracker.repository.ItemTrackerRepositoryBI;
import base.dto.AbstractDTOFactory;
import base.repository.AbstractRepositoryFinder;


public class AbstractServiceImpl {
	
	private AbstractRepositoryFinder repositoryFinder;

	public AbstractRepositoryFinder getRepositoryFinder(){
		return repositoryFinder;
	}
	
	public void setRepositoryFinder(AbstractRepositoryFinder repositoryFinder){
		this.repositoryFinder = repositoryFinder;
	}
	
	public ItemTrackerRepositoryBI getItemTrackerRespository() {
		return this.getRepositoryFinder().getItemTrackerRepository();
	}
}


