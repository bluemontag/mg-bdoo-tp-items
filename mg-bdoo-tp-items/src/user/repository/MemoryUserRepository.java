package user.repository;

import itemTracker.exception.UnknownUserException;
import user.domain.User;

public class MemoryUserRepository implements UserRepositoryBI{

	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}
}
