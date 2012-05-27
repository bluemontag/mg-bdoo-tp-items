package user.repository;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import base.repository.HibernateBaseRepository;
import user.domain.User;
import user.exception.UnknownUserException;

public class HibernetUserRepository extends HibernateBaseRepository implements UserRepositoryBI{


	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return User.class;
	}

	@Override
	public User getUserByOid(String anId) throws UnknownUserException {
		User anUser = (User) this.findeByOid(User.class, anId);
		if(anUser == null){
			throw new UnknownUserException();
		}
		return anUser;
	} 
	
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
