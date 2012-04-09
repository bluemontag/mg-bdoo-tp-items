package user.repository;

import user.domain.User;
import user.exception.UnknownUserException;

public class MemoryUserRepository implements UserRepositoryBI{
	
	@Override
	public User getUserByOid(String oid) throws UnknownUserException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}
}
