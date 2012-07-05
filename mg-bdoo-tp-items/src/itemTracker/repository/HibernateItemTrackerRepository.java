package itemTracker.repository;

import itemTracker.domain.ItemTracker;
import base.repository.HibernateBaseRepository;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class HibernateItemTrackerRepository extends HibernateBaseRepository implements ItemTrackerRepositoryBI{

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return ItemTracker.class;
	}
	
	@Override
	public ItemTracker getItemTracker() {
		return (ItemTracker) this.getEntityCriteria().uniqueResult();
	}
}
