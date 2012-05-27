package base.repository;

import proyect.repository.ProyectRepositoryBI;
import itemTracker.repository.ItemTrackerRepositoryBI;
import user.repository.UserRepositoryBI;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class AbstractRepositoryFinder {

	private ItemTrackerRepositoryBI itemTrackerRepository;
	private UserRepositoryBI userRepository;
	private ProyectRepositoryBI proyectRepository;

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
	
	public ProyectRepositoryBI getProyectRepository(){
		return this.proyectRepository;
	}
	
	public void setProyectRepository(ProyectRepositoryBI proyectRepository){
		this.proyectRepository = proyectRepository;
	}
	
}
