package itemTracker.domain;

import java.util.ArrayList;
import java.util.List;

import user.domain.User;

import itemTracker.exception.UnknownUserException;


public class ItemTracker {
	
	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
	private static ItemTracker instance;	
	
	private ArrayList<User> users;
//	private ArrayList<Proyecto> proyectos;

	
	/**
	 * @return the users
	 */
	public void addUser(User aUser) {
		this.users.add(aUser);
	}
	/**
	 * @param users the users to set
	 */
	public void setUsuarios(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * @param proyects the proyects to set
	 */
//	public void addProyecto(Proyecto proyecto) {
//		proyectos.add(proyecto);
//	}
	
	private ItemTracker(){
		this.users = new ArrayList<User>();
	}
	
	public static ItemTracker getInstance(){
		if (instance == null){
			return (new ItemTracker());
		}
		return instance;
	}
//
//	public String toString(){
//		return NOMBRE+ " / "+ DESCRIPCION;
//	}
//	
//	/*
//	 * 
//	 */
//	public void crearUsuario(String unNombreDeUsuario, String unPasswordEncriptado, Equipo equipo) throws UsuarioExistenteException{
//		
//		User nuevoUsuario = new User(unNombreDeUsuario, unPasswordEncriptado);
//		this.agregarUsuario(nuevoUsuario);
//	}
//	
//	public void crearTipoItem(String nombre, Workflow workflow)// Equipo?
//	{
//		
//	}
//	public void crearItem(TipoItem untipoItem, String unaDescripcion, String unaPrioridad){
//		
//	}
//	public void crearEstadoDeWorkflow(String unNombre, Workflow unWorkflow, List<Estado> proximosEstados){
//		
//	}
//	public void verHistoricoDeItem(Item unItem){
//		
//	}
//	public void cambiarEstadoDeItem(Item unItem, Transicion unaTransicion){
//		
//	}
//	public void listarItemsConEstadoEliminado(User unUsuario, Proyecto unProyecto){
//		
//	}
//	public void asignarEstadoAItem(Item unItem, Estado unEstado){
//		
//	}
//	public Boolean validarWorkflow(Workflow unWorkflow){
//		return true;
//	}
	

}
