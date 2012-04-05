package user.repository;

import itemTracker.exception.UnknownUserException;
import user.domain.User;

public interface UserRepositoryBI{
	
	User getUserByUserName(String anUserName) throws UnknownUserException;
}
