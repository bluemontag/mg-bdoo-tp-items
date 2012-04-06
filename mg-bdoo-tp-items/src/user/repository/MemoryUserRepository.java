package user.repository;

import user.domain.User;
import user.exception.UnknownUserException;

public class MemoryUserRepository implements UserRepositoryBI{

	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}
}
