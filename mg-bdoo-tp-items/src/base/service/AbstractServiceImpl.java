package base.service;

import base.repository.AbstractRepositoryFinder;


public class AbstractServiceImpl {
	
	private AbstractRepositoryFinder repositoryFinder;

	public AbstractRepositoryFinder getRepositoryFinder(){
		return repositoryFinder;
	}
	
	public void setRepositoryFinder(AbstractRepositoryFinder repositoryFinder){
		this.repositoryFinder = repositoryFinder;
	}
	
//	public AbstractItemTrackerReposiory getItemTrackerRespository() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}


