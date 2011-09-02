/**
 * 
 */
package proyecto.domain;

import java.util.ArrayList;
import java.util.Date;

import usuario.domain.Usuario;
import workflow.domain.Workflow;

import com.db4o.User;


/**
 * @author Rodrigo
 * 
 */
public class Proyecto {

	private String nombre;
	private Date fechaCreacion;
	private ArrayList<Usuario> miembros;
	private Usuario lider;
	private ArrayList<Workflow> workflows;

	public void agregarMiembro(Usuario unMiembro) {
		miembros.add(unMiembro);
	}

	/**
	 * @return the lider
	 */
	public Usuario getLider() {
		return lider;
	}

	/**
	 * @param lider
	 *            the lider to set
	 */
	public void setLider(Usuario unLider) {
		this.lider = unLider;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
