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
		this.createUserProjectLeader();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		// ojo que no se puede eliminar primero el usuario!
		this.deleteCreatedProject();
		this.deleteCreatedUserProjectLeader();
	}

	public void testCreateProject() {
		this.aCreatedProjectDTO = null;
		try {
			this.aCreatedProjectDTO = this.projectService.createProject(this.sessionToken,
					TestConstants.NEW_PROJECT_NAME, this.aCreatedProjectLeaderUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		assertNotNull(this.aCreatedProjectDTO);
		assertEquals(TestConstants.NEW_PROJECT_NAME, this.aCreatedProjectDTO.getName());
		assertEquals(this.aCreatedProjectLeaderUserDTO.getUserName(), this.aCreatedProjectDTO.getLeader().getUserName());
	}
}