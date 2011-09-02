package bugTracker.domain;

import item.domain.Item;
import item.domain.TipoItem;

import java.util.ArrayList;
import java.util.List;

import proyecto.domain.Proyecto;
import transicion.domain.Transicion;
import workflow.domain.Workflow;

import com.db4o.User;

import equipo.domain.Equipo;
import estado.domain.Estado;


public class BugTracker {
	
	public final static String NOMBRE = "Seguimiento de items";
	public final static String DESCRIPCION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
	private static BugTracker instance;	
	
	private ArrayList<User> users;
	private ArrayList<Proyecto> proyectos;

	
	/**
	 * @return the users
	 */
	public void addUser(User user) {
		users.add(user);
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * @return the users
	 */
	public void addProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
	}
	
	private BugTracker(){
		users = new ArrayList<User>();
	}
	public static BugTracker getInstance(){
		if (instance == null){
			return (new BugTracker());
		}
		return instance;
	}

	public String toString(){
		return NOMBRE+ " / "+ DESCRIPCION;
	}
	
	/*
	 * 
	 */
	public void crearUsuario(String unNombre, String unPassword, Equipo equipo){}
	
	public void crearTipoItem(String nombre, Workflow workflow)// Equipo?
	{
		
	}
	public void crearItem(TipoItem untipoItem, String unaDescripcion, String unaPrioridad){}
	public void crearEstadoDeWorkflow(String unNombre, Workflow unWorkflow, List<Estado> proximosEstados){}
	public void verHistoricoDeItem(Item unItem){}
	public void cambiarEstadoDeItem(Item unItem, Transicion unaTransicion){}
	public void listarItemsConEstadoEliminado(User unUsuario, Proyecto unProyecto){}
	public void asignarEstadoAItem(Item unItem, Estado unEstado){}
	public Boolean validarWorkflow(Workflow unWorkflow){
		return true;
	}
	

}
