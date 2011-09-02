/**
 * 
 */
package proyecto.domain;

import java.util.ArrayList;
import java.util.Date;

import workflow.domain.Workflow;

import com.db4o.User;


/**
 * @author Rodrigo
 * 
 */
public class Proyecto {

	private String nombre;
	private Date fechaCreacion;
	private ArrayList<User> miembros;
	private User lider;
	private ArrayList<Workflow> workflows;

	/**
	 * @return the users
	 */
	public void addUser(User miembro) {
		miembros.add(miembro);
	}

	/**
	 * @return the lider
	 */
	public User getLider() {
		return lider;
	}

	/**
	 * @param lider
	 *            the lider to set
	 */
	public void setLider(User lider) {
		this.lider = lider;
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
