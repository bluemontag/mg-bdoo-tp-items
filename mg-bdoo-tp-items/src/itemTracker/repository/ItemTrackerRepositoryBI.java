package itemTracker.repository;

import base.repository.HibernateBaseRepositoryBI;
import itemTracker.domain.ItemTracker;
import user.domain.User;

public interface ItemTrackerRepositoryBI extends HibernateBaseRepositoryBI{
	
	public abstract ItemTracker getItemTracker();
}
