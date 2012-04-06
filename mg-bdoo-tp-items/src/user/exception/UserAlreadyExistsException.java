package user.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserAlreadyExistsException extends BaseException {

	public UserAlreadyExistsException(String msj) {
		this.setMsj(msj);
	}

	private static final long serialVersionUID = 1L;
	
}
