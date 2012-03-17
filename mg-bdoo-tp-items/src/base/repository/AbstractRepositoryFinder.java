package base.repository;

import itemTracker.repository.AbstractItemTrackerRepository;
import user.repository.AbstractUserRepository;

public abstract class AbstractRepositoryFinder {

	AbstractItemTrackerRepository itemTrackerRepository;
	AbstractUserRepository userRepository;

	public AbstractItemTrackerRepository getItemTrackerRespository(){
		return this.itemTrackerRepository;
	}
	public void setUserRespository(AbstractItemTrackerRepository itemTrackerRepository){
		this.itemTrackerRepository = itemTrackerRepository;
	}
	
	public AbstractUserRepository getUserRespository(){
		return this.userRepository;
	}
	public void setUserRespository(AbstractUserRepository userRepository){
		this.userRepository = userRepository;
	}
	
}
