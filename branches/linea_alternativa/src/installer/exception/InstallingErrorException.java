package installer.exception;

import base.exception.BaseException;

public class InstallingErrorException extends BaseException{

	private static final long serialVersionUID = -1882928374475789871L;
	
	public InstallingErrorException(String msj) {
		super(msj);
	}
}
