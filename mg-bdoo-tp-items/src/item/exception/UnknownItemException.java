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
public class UnknownItemException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2563322421829400076L;

	public UnknownItemException(String msj) {
		super(msj);
	}

	public UnknownItemException() {
		super("El item que busca no existe.");
	}
}
