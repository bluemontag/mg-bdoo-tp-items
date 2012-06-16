package project.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UnknownProjectException extends BaseException {

	public UnknownProjectException(String msj) {
		super(msj);
	}

	private static final long serialVersionUID = 1L;

}
