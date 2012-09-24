package workflow.exception.state;
import base.exception.BaseException;


public class ItemStateAlreadyExistsException extends BaseException {

	/**
	 * @author ignacio 
	 */
	private static final long serialVersionUID = 3357163358059419817L;

	public ItemStateAlreadyExistsException(String msj) {
		super(msj);
	}		
}