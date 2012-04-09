package base.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class HibernateBaseRepository implements
		HibernateBaseRepositoryBI {

	private SessionFactory sessionFactory;

	public abstract Class getEntityClass();

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Query getNamedQuery(String queryName) {
		Query queryToReturn = this.getCurrentSession().getNamedQuery(queryName);
		return queryToReturn;
	}

	public Object findeByOid(Class<?> aClass, String anOid) {
		return this.getEntityById(anOid);
	}

	protected Criteria getEntityCriteria() {
		return this.getCurrentSession().createCriteria(getEntityClass());
	}

	protected Query getEntityQuery(String anHqlStringQuery) {
		return this.getCurrentSession().createQuery(anHqlStringQuery);
	}

	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected List getAllEntities() {
		return this.getEntityCriteria().list();
	}

	protected Object getEntityById(Serializable anId) {
		return this.getCurrentSession().get(getEntityClass(), anId);
	}
}
