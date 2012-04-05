package user.repository;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import itemTracker.exception.UnknownUserException;
import base.repository.HibernateBaseRepository;
import user.domain.User;

public class HibernetUserRepository extends HibernateBaseRepository implements UserRepositoryBI{

	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {

		User user = null;
		Query getUserByNameQuery = this.getNamedQuery("getUserByUserName");

		getUserByNameQuery.setParameter("anUserName", anUserName);
		getUserByNameQuery.setMaxResults(1);

		try{
			user = (User) getUserByNameQuery.uniqueResult();
		}catch(HibernateException notUniqueResultException){
			notUniqueResultException.printStackTrace();
		}

		if (user == null) {
				throw new UnknownUserException();
		}
		return user;
	}
}
