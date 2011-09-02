package base.command;

import com.opensymphony.xwork2.ActionSupport;

/*
 * Todos los commands heredan de este. Podemos aplicar comportamiento generico como filtros.
 */
public abstract class AbstractAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515078654701117288L;

	public String execute() throws Exception {

		this.preExecute();
		String resultado = this.doExecute();
		this.postExecute();
		
        return resultado;
    }

	/*
	 * Este metodo puede ser redefinido por las subclases para ejecutar
	 * comportamiento posterior al doExecute
	 */
	public void postExecute() {
		
	}

	/*
	 * Este metodo puede ser redefinido por las subclases para ejecutar
	 * comportamiento previo al doExecute
	 */
	
	public void preExecute() {
		// TODO Auto-generated method stub
		
	}

	protected abstract String doExecute();
}
