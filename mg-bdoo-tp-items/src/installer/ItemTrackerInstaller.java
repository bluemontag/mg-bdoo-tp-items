package installer;


import user.domain.User;

import itemTracker.domain.ItemTracker;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ItemTrackerInstaller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "SeguimientoDeItemsBaseDeDatos");
		try {
			ItemTracker itemTracker = ItemTracker.getInstance();
			User aUser = new User("rodrigo","rodrigo");
			itemTracker.agregarUsuario(aUser);
			db.store(itemTracker);
			System.out.println("Stored: "+itemTracker);
			System.out.println("Stored: "+aUser);
		}
		finally {
			db.close();
		}
	}

}
