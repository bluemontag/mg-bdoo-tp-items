package usuario.domain;

public class Usuario {

	private String nombreDeUsuario;
	private String password;
	
	/**
	 * @return the nombreDeUsuario
	 */
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	/**
	 * @param nombreDeUsuario the nombreDeUsuario to set
	 */
	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario(String unNombreDeUsuario, String unPassword) {
		this.nombreDeUsuario = unNombreDeUsuario;
		this.password = unPassword;
	}

}
