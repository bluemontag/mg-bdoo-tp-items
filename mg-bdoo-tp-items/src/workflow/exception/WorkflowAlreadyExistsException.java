package workflow.exception;
import base.exception.BaseException;


public class WorkflowAlreadyExistsException extends BaseException {

	/**
	 * @author ignacio 
	 */
	private static final long serialVersionUID = 3357163358059419817L;

	public WorkflowAlreadyExistsException(String msj) {
		super(msj);
	}		
}