package base.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 */
public abstract class HibernateBaseRepository extends HibernateDaoSupport implements HibernateBaseRepositoryBI {

	public Object findeById(Class<?> aClass, Long anId){
		return this.getSession().load(aClass, anId);
	}
}
