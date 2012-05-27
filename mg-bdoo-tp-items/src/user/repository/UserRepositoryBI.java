package user.repository;

import java.util.Collection;

import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;

public interface UserRepositoryBI{
	
	User getUserByUserName(String anUserName) throws UnknownUserException;

	User getUserByOid(String oid) throws UnknownUserException;

	User getUserByDTO(UserDTO aUserDTO) throws UnknownUserException;

	Collection<User> getUsersByDTOsList(Collection<UserDTOForLists> usersDTOs) throws UnknownUserException;
}
