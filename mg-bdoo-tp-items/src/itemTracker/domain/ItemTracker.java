package itemTracker.domain;

import java.util.Collection;
import java.util.HashSet;

import proyect.domain.Proyect;
import proyect.exception.UnknownProyectException;

import base.domain.BaseDomain;

import user.domain.User;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTracker extends BaseDomain{
	
	public final static String Name = "Seguimiento de items";
	public final static String DESCRIPTION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
		
	private Collection<User> users = new HashSet<User>();
	private User adminUser = null;
	private Collection<Proyect> proyects = new HashSet<Proyect>();

	public ItemTracker(){
		this.users = new HashSet<User>();
		this.setProyects(new HashSet<Proyect>());
	}
	
	public void addUser(User aUser) {
		this.users.add(aUser);
	}
	
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<User> getUsers() {
		return this.users;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User theAdminUser) {
		this.adminUser = theAdminUser;
	}
	
	public void setProyects(Collection<Proyect> proyects) {
		this.proyects = proyects;
	}

	public Collection<Proyect> getProyects() {
		return proyects;
	}
	
	public void addProyect(Proyect aProyect){
		this.proyects.add(aProyect);
	}

	public void logicalRemoveUser(User anUser) {
		anUser.setRemoved(true);
	}
	
	//usado solo por los tests para dejar la base como estaba
	@Deprecated
	public void removeUser(User anUser) throws UnknownUserException {
//		User anUserToRemove = null;
//		for(User user: this.users){
//			if(user.equals(anUser)){
//				anUserToRemove = user;
//				break;
//			}
//		}
//		if(anUserToRemove != null){
		boolean removed = this.users.remove(anUser);
//		}else{
		if(!removed){
			throw new UnknownUserException();
		}
//		}
	}
	
	public void logicalRemoveProyect(Proyect aProyect) {
		aProyect.setRemoved(true);
	}

	@Deprecated
	public void removeProyect(Proyect aProyect) throws UnknownProyectException {
		
		boolean removed = this.proyects.remove(aProyect);
		if(!removed){
			throw new UnknownProyectException("El proyecto que desea eliminar no existe.");
		}
	}
}
