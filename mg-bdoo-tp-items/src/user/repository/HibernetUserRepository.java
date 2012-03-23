package user.repository;

import base.repository.HibernateBaseRepository;
import user.domain.User;

public class HibernetUserRepository extends HibernateBaseRepository implements UserRepositoryBI{

	@Override
	public User getUserByUserName(String aUserName) {
		// Devolver el usuario desde base de datos
		return null;
	}

}
