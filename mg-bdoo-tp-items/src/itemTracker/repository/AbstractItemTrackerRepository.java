package itemTracker.repository;

import itemTracker.domain.ItemTracker;
import user.domain.User;

public abstract class AbstractItemTrackerRepository {
	
	
	// En teoria deberia haber uno solo.. TODO: ver como obtener el unico itemTracker
	public abstract ItemTracker getItemTrackerByName(String aUserName);
}
