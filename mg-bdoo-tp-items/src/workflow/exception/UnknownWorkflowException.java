package workflow.exception;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UnknownWorkflowException extends BaseException {

	private static final long serialVersionUID = 7822861035706803205L;

	public UnknownWorkflowException(String msj) {
		super(msj);
	}

}
