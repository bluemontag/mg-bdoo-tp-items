package user.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UnknownUserException extends BaseException {

	public UnknownUserException(String msj) {
		super(msj);
	}

	public UnknownUserException() {
		super("El usuario que busca no existe.");
	}

	private static final long serialVersionUID = 1L;
	
}
