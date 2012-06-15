package user.repository;

import java.util.Collection;
import java.util.HashSet;

import org.hibernate.Query;

import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.BaseException;
import base.repository.HibernateBaseRepository;

public class HibernetUserRepository extends HibernateBaseRepository implements UserRepositoryBI {

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return User.class;
	}

	@Override
	public User getUserByOid(String anOid) throws UnknownUserException {
		User anUser = (User) this.findeByOid(this.getEntityClass(), anOid);
		if (anUser == null) {
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
			throw new UnknownUserException("El usuario " + anUserName + " no existe.");
		}
		return user;
	}

	@Override
	public User getUserByDTO(UserDTO aUserDTO) throws UnknownUserException {
		return this.getUserByOid(aUserDTO.getOid());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> getUsersByDTOsList(Collection<UserDTOForLists> usersDTOs) throws UnknownUserException {

		Collection<String> oids = this.getOidsFromColleccionOfDTOs(usersDTOs);

		Query aQuery = this.getNamedQuery("getUsersByOids");

		aQuery.setParameterList("oids", oids);
		aQuery.setParameter("isRemoved", false);

		Collection<User> users = aQuery.list();

		if (users == null) {
			throw new UnknownUserException("No se encontro ningun usuario de la lista.");
		}
		if (users.size() != usersDTOs.size()) {
			throw new UnknownUserException("Alguno de los usuarios de la lista no se encontro o fue eliminado.");
		}
		return new HashSet<User>(users);

	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> getUsersByDTOs(Collection<UserDTO> usersDTOs) throws UnknownUserException {

		Collection<String> oids = this.getOidsFromColleccionOfDTOs(usersDTOs);

		Query aQuery = this.getNamedQuery("getUsersByOids");

		aQuery.setParameterList("oids", oids);
		aQuery.setParameter("isRemoved", false);

		Collection<User> users = aQuery.list();

		if (users == null) {
			throw new UnknownUserException("No se encontro ningun usuario de la lista.");
		}
		if (users.size() != usersDTOs.size()) {
			throw new UnknownUserException("Alguno de los usuarios de la lista no se encontro o fue eliminado.");
		}
		return new HashSet<User>(users);

	}
}
