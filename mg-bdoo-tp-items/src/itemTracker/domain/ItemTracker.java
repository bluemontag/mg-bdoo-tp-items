package itemTracker.domain;


import java.awt.List;
import java.util.HashSet;
import java.util.Set;

import base.domain.BaseDomain;

import user.domain.User;

//import itemTracker.exception.UnknownUserException;


public class ItemTracker extends BaseDomain{
	
	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
	private static ItemTracker instance;	
	private String name; // TODO: borrar
	
	private Set<User> users = new HashSet<User>();
//	private ArrayList<Proyecto> proyectos;

	public ItemTracker(String aName){
		this.setName(aName); // TODO: borrar
	}
	/**
	 * @return the users
	 */
	public void addUser(User aUser) {
		this.users.add(aUser);
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @returns users
	 */
	public Set<User> getUsers() {
		return this.users;
	}
	
	/**
	 * @param proyects the proyects to set
	 */
//	public void addProyecto(Proyecto proyecto) {
//		proyectos.add(proyecto);
//	}
	
	private ItemTracker(){
		this.users = new HashSet<User>();
	}
	
	public static ItemTracker getInstance(){
		if (instance == null){
			ItemTracker itemTracker = new ItemTracker("Item tracker");
			return itemTracker;
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
	 // TODO: borrar
	public void setName(String name) {
		this.name = name;
	}
	 // TODO: borrar
	public String getName() {
		return name;
	}
	

}
