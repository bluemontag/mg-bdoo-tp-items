package item.exception.itemType;

import base.exception.BaseException;

/**
 * @author igallego ignaciogallego@gmail.com
 *  
 * 20/06/2012
 */
public class UnknownItemTypeException extends BaseException {

	private static final long serialVersionUID = 3854853601185445664L;

	public UnknownItemTypeException (String msj) {
		super(msj);
	}

	public UnknownItemTypeException() {
		super("Tipo de item desconocido.");
	}

}