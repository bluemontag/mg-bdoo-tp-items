package user.repository;

import user.domain.User;

public class MemoryUserRepository implements UserRepositoryBI{

	@Override
	public User getUserByUserName(String aUserName) {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}

}