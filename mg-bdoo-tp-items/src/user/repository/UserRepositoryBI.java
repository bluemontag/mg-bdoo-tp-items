package user.repository;

import user.domain.User;

public interface UserRepositoryBI{
	
	User getUserByUserName(String anUserName);
}
