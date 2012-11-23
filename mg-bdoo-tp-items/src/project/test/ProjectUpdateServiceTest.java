package project.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectUpdateServiceTest extends ProjectServiceTest {

	private UserDTO anUserDTO;
	private ProjectDTO aProjectDTO;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.anUserDTO = this.createUser(TestConstants.NEW_USER_NAME, TestConstants.USER_PASSWORD);
		this.aProjectDTO = this.createProject(TestConstants.NEW_PROJECT_NAME, this.anUserDTO);
		this.aUserDTOForListCollection = this.createAUserCollection(ProjectUpdateServiceTest.class.toString());
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.aProjectDTO = this.getProject(this.aProjectDTO);
		this.deleteProject(this.aProjectDTO);
		this.deleteUser(this.anUserDTO);
		this.removeTheUserCollection(ProjectUpdateServiceTest.class.toString());
	}

	@Test
	public void testUpdateProject() {
		this.aProjectDTO.setUsersFromDTOsForList(this.aUserDTOForListCollection);
		this.aProjectDTO.setName(TestConstants.UPDATED_PROJECT_NAME);
		try {
			this.projectService.updateProject(this.sessionToken, this.aProjectDTO);
		} catch (UnknownProjectException e) {
			fail("El proyecto que se quiere modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("Error de concurrencia de DTO: Esto no deberia pasar ya que es un test controlado.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios no existe.");
		}
		this.assertsOnUpdateProjectService();
	}

	private void assertsOnUpdateProjectService() {

		ProjectDTO anUpdatedProjectDTO = null;

		try {
			anUpdatedProjectDTO = this.projectService.getProject(this.sessionToken, this.aProjectDTO);
		} catch (UnknownProjectException e) {
			fail("No deberia pasar esto!");
		}

		assertEquals(this.aProjectDTO.getName(), anUpdatedProjectDTO.getName());
		assertNotSame(this.aProjectDTO.getVersion(), anUpdatedProjectDTO.getVersion());

		boolean existe = false;
		for (UserDTOForLists userDTOForList : this.aProjectDTO.getUsers()) {
			existe = false;
			for (UserDTOForLists userDTOForListOnUpdateProject : anUpdatedProjectDTO.getUsers()) {
				if (userDTOForList.equals(userDTOForListOnUpdateProject)) {
					existe = true;
					break;
				}
			}
			assertTrue(existe);
		}
	}
}