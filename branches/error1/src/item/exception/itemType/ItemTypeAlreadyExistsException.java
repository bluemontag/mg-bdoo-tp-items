package item.exception.itemType;

import base.exception.BaseException;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 20/06/2012
 */
public class ItemTypeAlreadyExistsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2027889218279476143L;

	public ItemTypeAlreadyExistsException (String msj) {
		super(msj);
	}

	public ItemTypeAlreadyExistsException() {
		super("El tipo de item ya existe.");
	}

}