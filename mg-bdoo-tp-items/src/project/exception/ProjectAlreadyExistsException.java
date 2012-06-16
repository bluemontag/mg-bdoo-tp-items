package project.exception;

import base.exception.BaseException;

public class ProjectAlreadyExistsException extends BaseException {

	public ProjectAlreadyExistsException(String msj) {
		super(msj);
	}

	private static final long serialVersionUID = -3934191269014321699L;

}
