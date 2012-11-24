package project.test;

import org.junit.After;
import org.junit.Before;

import project.dto.ProjectDTO;
import project.exception.ProjectAlreadyExistsException;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectCreateServiceTest extends ProjectServiceTest {

	private UserDTO anUserDTO;
	private ProjectDTO aProjectDTO;
	private String testCode;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + ProjectCreateServiceTest.class.getSimpleName();
		this.anUserDTO = this.createUser(TestConstants.USER_NAME, TestConstants.USER_PASSWORD);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.deleteProject(this.aProjectDTO);
		this.deleteUser(this.anUserDTO);
	}

	public void testCreateProject() {
		this.aProjectDTO = null;
		try {
			this.aProjectDTO = this.projectService.createProject(this.sessionToken, TestConstants.PROJECT_NAME
					+ testCode, this.anUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		assertNotNull(this.aProjectDTO);
		assertEquals(TestConstants.PROJECT_NAME + testCode, this.aProjectDTO.getName());
		assertEquals(this.anUserDTO.getUserName(), this.aProjectDTO.getLeader().getUserName());
	}
}