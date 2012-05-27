package base.service;

import proyect.service.ProyectServiceBI;
import itemTracker.service.ItemTrackerServiceBI;
import user.service.UserServiceBI;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ServiceFinder {

	private static ServiceFinder instance;
	private ItemTrackerServiceBI itemTrackerService;
	private UserServiceBI userService;
	private ProyectServiceBI proyectService;
	
	/**
	 * Método estático que permite acceder a la única instancia de esta clase.
	 * 
	 * @return la única instancia de esta clase.
	 */
	public static ServiceFinder getInstance() {
		if (instance == null) {
			instance = new ServiceFinder();
		}
		return instance;
	}

	public ItemTrackerServiceBI getItemTrackerService() {
		return itemTrackerService;
	}

	public void setItemTrackerService(ItemTrackerServiceBI itemTrackerService) {
		this.itemTrackerService = itemTrackerService;
	}

	public UserServiceBI getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBI userService) {
		this.userService = userService;
	}

	public ProyectServiceBI getProyectService() {
		return proyectService;
	}

	public void setProyectService(ProyectServiceBI userService) {
		this.proyectService = userService;
	}

}


