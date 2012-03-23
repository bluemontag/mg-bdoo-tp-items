package itemTracker.repository;

import base.repository.HibernateBaseRepositoryBI;
import itemTracker.domain.ItemTracker;
import user.domain.User;

public interface ItemTrackerRepositoryBI extends HibernateBaseRepositoryBI{
	
	
	// En teoria deberia haber uno solo.. TODO: ver como obtener el unico itemTracker
	public abstract ItemTracker getItemTrackerByName(String aUserName);
}
