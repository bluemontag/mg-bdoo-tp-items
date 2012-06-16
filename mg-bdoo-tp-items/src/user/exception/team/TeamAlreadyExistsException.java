package user.exception.team;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamAlreadyExistsException extends BaseException {

	private static final long serialVersionUID = 6941862432334796722L;

	public TeamAlreadyExistsException(String msj) {
		super(msj);
	}

}
