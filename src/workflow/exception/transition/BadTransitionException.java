/**
 * 
 */
package workflow.exception.transition;

/**
 * @author ignacio
 *
 */
import base.exception.BaseException;

public class BadTransitionException extends BaseException {

	private static final long serialVersionUID = 7536662591583451768L;

	public BadTransitionException(String msj) {
		super(msj);
	}

}