package proyect.test;

import org.junit.After;
import org.junit.Before;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectCreateServiceTest extends ProyectServiceTest {

	public final static String NEW_PROYECT_NAME = "A proyect name";

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		try {
			// se crea un usuario para setear como lider del proyecto.
			this.aCreatedProyectLeaderUserDTO = this.userService.createUser("userProyectLeaderTest", "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {

		ProyectDTO aProyectDTOToRemove = this.proyectService.getProyect(this.aCreatedProyectDTO);
		UserDTO anUserDTOToRemove = this.userService.getUser(this.aCreatedProyectLeaderUserDTO);

		// Se elimina el proyecto para dejar la base como estaba.
		this.proyectService.removeProyect(aProyectDTOToRemove);

		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		this.userService.removeUser(anUserDTOToRemove);
	}

	public void testCreateProyect() {
		ProyectDTO aCreatedProyectDTO = null;
		try {
			aCreatedProyectDTO = this.proyectService.createProyect(NEW_PROYECT_NAME, this.aCreatedProyectLeaderUserDTO);
		} catch (ProyectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		assertNotNull(aCreatedProyectDTO);
		assertEquals(NEW_PROYECT_NAME, aCreatedProyectDTO.getName());
		assertEquals(this.aCreatedProyectLeaderUserDTO.getUserName(), aCreatedProyectDTO.getLeader().getUserName());
	}

}