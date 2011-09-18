package installer;


import itemTracker.domain.BugTracker;
import usuario.domain.Usuario;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.User;

public class ItemTrackerInstaller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded
				.newConfiguration(), "SeguimientoDeItemsBaseDeDatos");
		try {
			BugTracker sistema = BugTracker.getInstance();
			Usuario unUsuario = new Usuario("rodrigo","rodrigo");
			sistema.agregarUsuario(unUsuario);
			db.store(sistema);
			System.out.println("Stored: "+sistema);
			System.out.println("Stored: "+unUsuario);
		}
		finally {
			db.close();
		}
	}

}
