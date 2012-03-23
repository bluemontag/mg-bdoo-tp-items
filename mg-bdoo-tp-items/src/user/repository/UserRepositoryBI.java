package user.repository;

import base.repository.HibernateBaseRepositoryBI;
import user.domain.User;

public interface UserRepositoryBI  extends HibernateBaseRepositoryBI{
	
	User getUserByUserName(String aUserName);
}
