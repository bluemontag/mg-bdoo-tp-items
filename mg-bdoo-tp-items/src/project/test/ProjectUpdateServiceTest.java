package project.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectUpdateServiceTest extends ProjectServiceTest {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.createUser();
		this.createProject();
		this.createAUserCollection();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.deleteCreatedProject();
		this.deleteUser();
		this.removeTheUserCollection();
	}

	@Test
	public void testUpdateProject() {
		this.aCreatedProjectDTO.setUsersFromDTOsForList(this.aUserDTOForListCollection);
		this.aCreatedProjectDTO.setName(TestConstants.UPDATED_PROJECT_NAME);
		try {
			this.projectService.updateProject(this.sessionToken, this.aCreatedProjectDTO);
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
			anUpdatedProjectDTO = this.projectService.getProject(this.sessionToken, this.aCreatedProjectDTO);
		} catch (UnknownProjectException e) {
			fail("No deberia pasar esto!");
		}

		assertEquals(this.aCreatedProjectDTO.getName(), anUpdatedProjectDTO.getName());
		assertNotSame(this.aCreatedProjectDTO.getVersion(), anUpdatedProjectDTO.getVersion());

		boolean existe = false;
		for (UserDTOForLists userDTOForList : this.aCreatedProjectDTO.getUsers()) {
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