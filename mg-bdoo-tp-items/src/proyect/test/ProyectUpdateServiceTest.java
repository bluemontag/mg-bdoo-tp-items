package proyect.test;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;

import user.dto.UserDTO;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectUpdateServiceTest extends ProyectServiceTest {

	Collection<UserDTO> usuariosAAsignarAProyecto = new HashSet<UserDTO>();

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		this.createUserProyectLeader();
		this.createProyect();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		// ojo que no se puede eliminar primero el usuario!
		this.deleteCreatedProyect();
		this.deleteCreatedUserProyectLeader();
	}

	public void testUpdateProyect() {

	}
}