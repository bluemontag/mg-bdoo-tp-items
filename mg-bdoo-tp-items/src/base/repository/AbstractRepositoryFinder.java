package base.repository;

import itemTracker.repository.ItemTrackerRepositoryBI;
import user.repository.UserRepositoryBI;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractRepositoryFinder {

	ItemTrackerRepositoryBI itemTrackerRepository;
	UserRepositoryBI userRepository;

	public ItemTrackerRepositoryBI getItemTrackerRepository(){
		return this.itemTrackerRepository;
	}
	public void setItemTrackerRepository(ItemTrackerRepositoryBI itemTrackerRepository){
		this.itemTrackerRepository = itemTrackerRepository;
	}
	
	public UserRepositoryBI getUserRepository(){
		return this.userRepository;
	}
	public void setUserRepository(UserRepositoryBI userRepository){
		this.userRepository = userRepository;
	}
	
}
