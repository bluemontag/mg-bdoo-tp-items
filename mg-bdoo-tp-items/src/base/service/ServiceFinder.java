package base.service;

import itemTracker.service.ItemTrackerServiceBI;
import user.service.UserServiceBI;

public class ServiceFinder {

	private ItemTrackerServiceBI itemTrackerService;
	private UserServiceBI userService;
	
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


