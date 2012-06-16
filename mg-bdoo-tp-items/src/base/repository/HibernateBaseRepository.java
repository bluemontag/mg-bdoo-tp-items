package base.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import base.domain.BaseDomain;
import base.dto.AbstractDTO;
import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class HibernateBaseRepository {

	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
	protected List getAllEntities() {
		return this.getEntityCriteria().list();
	}

	protected Object getEntityById(Serializable anId) {
		return this.getCurrentSession().get(getEntityClass(), anId);
	}

	public BaseDomain getEntityByUniqueField(String namedQuery, String parameterName, Object searched)
			throws BaseException {
		BaseDomain aBaseDomainObject = null;
		Query getBaseDomainObjectByNamedQuery = this.getNamedQuery(namedQuery);

		getBaseDomainObjectByNamedQuery.setParameter(parameterName, searched);
		getBaseDomainObjectByNamedQuery.setMaxResults(1);

		try {
			aBaseDomainObject = (BaseDomain) getBaseDomainObjectByNamedQuery.uniqueResult();
		} catch (HibernateException notUniqueResultException) {
			new BaseException("Inesperado!: existe mas de una entidad con el mismo campo.");
		}
		return aBaseDomainObject;
	}

	public Collection<String> getOidsFromColleccionOfDTOs(Collection<? extends AbstractDTO> dtoCollection) {
		Collection<String> oids = new HashSet<String>();
		for (AbstractDTO dto : dtoCollection) {
			oids.add(dto.getOid());
		}
		return oids;
	}
}
