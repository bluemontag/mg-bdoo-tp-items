package base.exception;

public class StoreConcurrencyException extends RuntimeException {

	private static final long serialVersionUID = 5563455232880877756L;

	public StoreConcurrencyException(String msj) {
		super(msj);
	}

}
