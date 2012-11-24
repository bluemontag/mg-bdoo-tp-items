package project.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import project.dto.ProjectDTO;
import project.exception.ProjectAlreadyExistsException;
import project.exception.UnknownProjectException;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.test.BaseTestCase;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class ProjectServiceTest extends BaseTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("rawtypes")
	public static Collection<Class> getClassesTestToPerform() {
		// en este metodo se agregan todos los test a realizar relacionados
		Collection<Class> projectTestsClasses = new ArrayList<Class>();
		projectTestsClasses.add(ProjectCreateServiceTest.class);
		projectTestsClasses.add(ProjectUpdateServiceTest.class);
		return projectTestsClasses;
	}

	protected ProjectDTO createProject(String newProjectName, UserDTO anUserDTO) {
		ProjectDTO aProjectDTO = null;
		try {
			aProjectDTO = this.projectService.createProject(this.sessionToken, newProjectName, anUserDTO);
		} catch (ProjectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
		return aProjectDTO;
	}

	protected void deleteProject(ProjectDTO aProjectDTO) {
		try {
			this.projectService.removeProject(this.sessionToken, aProjectDTO);
		} catch (UnknownProjectException e) {
			fail("El proyecto que desea eliminar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El proyecto que desea eliminar fue modificado por otro usuario.");
		}
	}

	protected ProjectDTO getProject(ProjectDTO aProjectDTO) {
		ProjectDTO aProjectDTOAux = null;
		try {
			aProjectDTOAux = this.projectService.getProject(sessionToken, aProjectDTO);
		} catch (UnknownProjectException e) {
			fail("No se encontro el proyecto.");
		}
		return aProjectDTOAux;
	}

}
