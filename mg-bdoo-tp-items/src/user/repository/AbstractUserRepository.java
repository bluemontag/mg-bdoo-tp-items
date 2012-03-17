package user.repository;

import user.domain.User;

public abstract class AbstractUserRepository {
	
	public abstract User getUserByUserName(String aUserName);
}
