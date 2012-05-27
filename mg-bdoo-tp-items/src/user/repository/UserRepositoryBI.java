package user.repository;

import user.domain.User;
import user.dto.UserDTO;
import user.exception.UnknownUserException;

public interface UserRepositoryBI{
	
	User getUserByUserName(String anUserName) throws UnknownUserException;

	User getUserByOid(String oid) throws UnknownUserException;

	User getUserByDTO(UserDTO aUserDTO) throws UnknownUserException;
}
