package user.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class IncorrectPasswordException extends BaseException {

	private static final long serialVersionUID = 3441403316054145270L;

	public IncorrectPasswordException(String msj) {
		super(msj);
	}
}
