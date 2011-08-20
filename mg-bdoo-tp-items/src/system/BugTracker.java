package system;

import java.util.ArrayList;

import com.db4o.User;

public class BugTracker {
	
	public final static String NOMBRE = "Seguimiento de items";
	public final static String DESCRIPCION = "Trabajo practico - Mg. Ing. de Softare - BDOO";
	private static BugTracker instance;	
	
	private ArrayList<User> users;

	
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
		return NOMBRE+" / "+DESCRIPCION;
	}
}
