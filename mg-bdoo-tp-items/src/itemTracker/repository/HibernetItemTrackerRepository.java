package itemTracker.repository;

import base.repository.HibernateBaseRepository;
import itemTracker.domain.ItemTracker;

public class HibernetItemTrackerRepository extends HibernateBaseRepository implements ItemTrackerRepositoryBI{

	@Override
	public ItemTracker getItemTracker() {
		return (ItemTracker) this.findeById(ItemTracker.class, new Long(1));
	}

}
