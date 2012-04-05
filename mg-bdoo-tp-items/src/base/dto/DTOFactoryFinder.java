package base.dto;

import user.dto.UserDTOFactory;
import itemTracker.dto.ItemTrackerDTOFactory;

// TODO: creo que esto no va..
public class DTOFactoryFinder{
	
	private UserDTOFactory userDTOFactory;
	private ItemTrackerDTOFactory itemTrackerDTOFactory;
	
	public void setUserDTOFactory(UserDTOFactory userDTOFactory) {
		this.userDTOFactory = userDTOFactory;
	}
	public UserDTOFactory getUserDTOFactory() {
		return userDTOFactory;
	}
	public void setItemTrackerDTOFactory(ItemTrackerDTOFactory itemTrackerDTOFactory) {
		this.itemTrackerDTOFactory = itemTrackerDTOFactory;
	}
	public ItemTrackerDTOFactory getItemTrackerDTOFactory() {
		return itemTrackerDTOFactory;
	}
}
