/**
 * 
 */
package item.exception;

import base.exception.BaseException;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 15/06/2012
 */
public class ItemAlreadyExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2027889218279476143L;

	public ItemAlreadyExistsException (String msj) {
		super(msj);
	}

	public ItemAlreadyExistsException() {
		super("El item ya existe.");
	}

	
	
}
