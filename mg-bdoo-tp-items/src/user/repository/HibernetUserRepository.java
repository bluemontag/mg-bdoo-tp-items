package user.repository;

import user.domain.User;

public class HibernetUserRepository extends AbstractUserRepository{

	@Override
	public User getUserByUserName(String aUserName) {
		// Devolver el usuario desde base de datos
		return null;
	}

}
