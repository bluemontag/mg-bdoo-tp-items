package workflow.exception.state;

import base.exception.BaseException;

/**
 * @author Ignacio Gallego
 */
public class UnknownItemStateException extends BaseException {

	private static final long serialVersionUID = 7822861035706803205L;

	public UnknownItemStateException(String msj) {
		super(msj);
	}

	public UnknownItemStateException() {
		super("");
	}

}
