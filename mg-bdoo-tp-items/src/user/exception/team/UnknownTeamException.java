package user.exception.team;

import base.exception.BaseException;

public class UnknownTeamException extends BaseException {

	private static final long serialVersionUID = 4119274027334941498L;

	public UnknownTeamException(String msj) {
		super(msj);
	}

}
