package user.repository;

import base.exception.BaseException;
import base.repository.HibernateBaseRepository;
import user.domain.User;
import user.dto.UserDTO;
import user.exception.UnknownUserException;

public class HibernetUserRepository extends HibernateBaseRepository implements UserRepositoryBI{


	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return User.class;
	}

	@Override
	public User getUserByOid(String anOid) throws UnknownUserException {
		User anUser = (User) this.findeByOid(this.getEntityClass(), anOid);
		if(anUser == null){
			throw new UnknownUserException();
		}
		return anUser;
	} 
	
	@Override
	public User getUserByUserName(String anUserName) throws UnknownUserException {

		User user = null;
		try {
			user = (User) this.getEntityByUniqueField("getUserByUserName", "anUserName", anUserName);
		} catch (BaseException aBaseException) {
			// TODO ver que hacer!!
		}
		if (user == null) {
				throw new UnknownUserException("El usuario "+ anUserName +" no existe.");
		}
		return user;
	}

	@Override
	public User getUserByDTO(UserDTO aUserDTO) throws UnknownUserException {
		return this.getUserByOid(aUserDTO.getOid());
	}
}
