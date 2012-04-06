package base.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Query;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class HibernateBaseRepository extends HibernateDaoSupport implements HibernateBaseRepositoryBI {

	protected Query getNamedQuery(String queryName) {
		Query queryToReturn = this.getSession().getNamedQuery(queryName);
		return queryToReturn;
	}
	
	public Object findeById(Class<?> aClass, Long anId){
		return this.getSession().load(aClass, anId);
	}
}
