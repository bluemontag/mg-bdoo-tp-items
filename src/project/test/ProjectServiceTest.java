package project.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import project.dto.ProjectDTO;
import project.exception.ProjectAlreadyExistsException;
import project.exception.UnknownProjectException;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.test.BaseTestCase;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class ProjectServiceTest extends BaseTestCase {

	protected ProjectDTO aCreatedProjectDTO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	protected void createProject() {
		try {
			// se crea el proyecto que se va a updetear
			this.aCreatedProjectDTO = this.projectService.createProject(this.sessionToken,
					TestConstants.NEW_PROJECT_NAME, this.aCreatedUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
	}

	protected void deleteCreatedProject() {
		try {
			// se optiene el proyecto por su fue modificado.
			ProjectDTO aProjectDTOToRemove = this.projectService.getProject(this.sessionToken, this.aCreatedProjectDTO);
			this.projectService.removeProject(this.sessionToken, aProjectDTOToRemove);
		} catch (UnknownProjectException e) {
			fail("El proyecto que desea eliminar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El proyecto que desea eliminar fue modificado por otro usuario.");
		}
	}

}
