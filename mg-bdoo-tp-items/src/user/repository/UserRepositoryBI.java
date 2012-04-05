package user.repository;

import base.repository.HibernateBaseRepositoryBI;
import user.domain.User;

public interface UserRepositoryBI{
	
	User getUserByUserName(String aUserName);
}
