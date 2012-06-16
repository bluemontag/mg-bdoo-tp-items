package workflow.exception;

import base.exception.BaseException;

public class UnknownWorkflowException extends BaseException {

	private static final long serialVersionUID = 7822861035706803205L;

	public UnknownWorkflowException(String msj) {
		super(msj);
	}

}
