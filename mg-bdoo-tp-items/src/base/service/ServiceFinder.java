package base.service;

import itemTracker.service.ItemTrackerServiceBI;
import user.service.UserServiceBI;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ServiceFinder {

	private static ServiceFinder instance;
	private ItemTrackerServiceBI itemTrackerService;
	private UserServiceBI userService;
	
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
	
	/**
	 * @return the itemTrackerService
	 */
	public ItemTrackerServiceBI getItemTrackerService() {
		return itemTrackerService;
	}
	/**
	 * @param itemTrackerService the itemTrackerService to set
	 */
	public void setItemTrackerService(ItemTrackerServiceBI itemTrackerService) {
		this.itemTrackerService = itemTrackerService;
	}
	/**
	 * @return the userService
	 */
	public UserServiceBI getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserServiceBI userService) {
		this.userService = userService;
	}


}


