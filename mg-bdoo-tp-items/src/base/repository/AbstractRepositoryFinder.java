package base.repository;

import user.repository.AbstractUserRepository;

public abstract class AbstractRepositoryFinder {

	AbstractUserRepository userRepository;
	
	public AbstractUserRepository getUserRespository(){
		return this.userRepository;
	}
	
}
