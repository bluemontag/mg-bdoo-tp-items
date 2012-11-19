package workflow.exception.transition;

import base.exception.BaseException;

/**
 * @author Rodrigo Itursarry itursarry@gmail.com
 */
public class UnknownTransitionException extends BaseException {

	private static final long serialVersionUID = 4153851021636063166L;

	public UnknownTransitionException(String msj) {
		super(msj);
	}

}
