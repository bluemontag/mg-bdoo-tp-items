package itemTracker.repository;

import base.repository.HibernateBaseRepository;
import itemTracker.domain.ItemTracker;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class HibernetItemTrackerRepository extends HibernateBaseRepository implements ItemTrackerRepositoryBI{

	@Override
	public Class getEntityClass() {
		return ItemTracker.class;
	}
	
	@Override
	public ItemTracker getItemTracker() {
		return (ItemTracker) this.getEntityCriteria().uniqueResult();
	}
}
