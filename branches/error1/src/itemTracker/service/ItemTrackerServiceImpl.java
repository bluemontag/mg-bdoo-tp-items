/**
 * 
 */
package itemTracker.service;

import user.domain.User;
import user.dto.UserDTO;
import user.dto.UserDTOFactory;
import user.exception.IncorrectPasswordException;
import user.exception.UnknownUserException;
import base.domain.OidGenerator;
import base.security.ItemTrackerSession;
import base.service.AbstractServiceImpl;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTrackerServiceImpl extends AbstractServiceImpl implements ItemTrackerServiceBI {

	@Override
	public String loginUser(String anUserName, String aPassword) throws UnknownUserException,
			IncorrectPasswordException {
		User userToBeLogged = this.getUserRespository().getUserByUserName(anUserName);
		String aPasswordEncrypted = aPassword; // TODO: encriptar
		if (!userToBeLogged.getPassword().equals(aPasswordEncrypted)) {
			throw new IncorrectPasswordException("Contraseña incorrecta.");
		}
		// se genera el DTO de usuario.
		UserDTO userToLogin = (UserDTO) UserDTOFactory.getInstance().getDTO(userToBeLogged);
		// se genera el token de session.
		String sessionToken = OidGenerator.createId();
		// se guarda la session de usuario con el respectivo token.
		ItemTrackerSession.getInstance().addUserDTOLogged(sessionToken, userToLogin);
		return sessionToken;
	}
}
