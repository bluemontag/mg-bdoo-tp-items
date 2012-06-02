package proyect.test;

import org.junit.After;
import org.junit.Before;

import proyect.exception.ProyectAlreadyExistsException;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectCreateServiceTest extends ProyectServiceTest {

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		this.createUserProyectLeader();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		// ojo que no se puede eliminar primero el usuario!
		this.deleteCreatedProyect();
		this.deleteCreatedUserProyectLeader();
	}

	public void testCreateProyect() {
		this.aCreatedProyectDTO = null;
		try {
			this.aCreatedProyectDTO = this.proyectService.createProyect(NEW_PROYECT_NAME,
					this.aCreatedProyectLeaderUserDTO);
		} catch (ProyectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		assertNotNull(this.aCreatedProyectDTO);
		assertEquals(NEW_PROYECT_NAME, this.aCreatedProyectDTO.getName());
		assertEquals(this.aCreatedProyectLeaderUserDTO.getUserName(), this.aCreatedProyectDTO.getLeader().getUserName());
	}

}