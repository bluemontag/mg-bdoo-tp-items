package user.repository;

import user.domain.User;
import user.exception.UnknownUserException;

public interface UserRepositoryBI{
	
	User getUserByUserName(String anUserName) throws UnknownUserException;
}
