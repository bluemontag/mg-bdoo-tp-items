package installer;


import bugTracker.domain.BugTracker;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.User;

public class BugTrackerInstaller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "SeguimientoDeItemsBaseDeDatos");
		try {
			BugTracker sistema = BugTracker.getInstance();
			User user = new User("rodrigo","rodrigo");
			sistema.addUser(user);
			db.store(sistema);
			System.out.println("Stored: "+sistema);
			System.out.println("Stored: "+user);
		}
		finally {
			db.close();
		}
	}

}
