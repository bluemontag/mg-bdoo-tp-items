package project.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectAlreadyExistsException extends BaseException {

	public ProjectAlreadyExistsException(String msj) {
		super(msj);
	}

	private static final long serialVersionUID = -3934191269014321699L;

}
