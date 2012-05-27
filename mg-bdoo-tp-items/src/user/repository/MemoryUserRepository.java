package user.repository;

import user.domain.User;
import user.dto.UserDTO;
import user.exception.UnknownUserException;

public class MemoryUserRepository implements UserRepositoryBI{
	
	@Override
	public User getUserByOid(String oid) throws UnknownUserException {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}
	
	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {
		// Devolver el usuario desde lo que esta cargado en memoria.
		return null;
	}

	@Override
	public User getUserByDTO(UserDTO aUserDTO) throws UnknownUserException {
		return this.getUserByOid(aUserDTO.getOid());
	}
}
