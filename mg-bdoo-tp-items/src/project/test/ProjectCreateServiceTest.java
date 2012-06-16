package project.test;

import org.junit.After;
import org.junit.Before;

import project.exception.ProjectAlreadyExistsException;
import user.exception.UnknownUserException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectCreateServiceTest extends ProjectServiceTest {

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
			this.aCreatedProyectDTO = this.proyectService.createProyect(this.sessionToken,
					TestConstants.NEW_PROYECT_NAME, this.aCreatedProyectLeaderUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		assertNotNull(this.aCreatedProyectDTO);
		assertEquals(TestConstants.NEW_PROYECT_NAME, this.aCreatedProyectDTO.getName());
		assertEquals(this.aCreatedProyectLeaderUserDTO.getUserName(), this.aCreatedProyectDTO.getLeader().getUserName());
	}
}