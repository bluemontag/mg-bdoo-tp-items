package base.exception;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 1L;
	protected String msj="";
	
	public BaseException(String msj) {
		this.msj = msj;
	}
	public void setMsj(String msj){
		this.msj = msj;
	}
	public String getMsj(){
		return this.msj;
	}
}
