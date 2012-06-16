package project.test;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.dto.ProjectDTO;
import project.exception.UnknownProjectException;
import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProjectUpdateServiceTest extends ProjectServiceTest {

	private final Collection<UserDTO> usuariosAAsignarAProyecto = new HashSet<UserDTO>();

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		this.createUserProyectLeader();
		this.createProyect();
		this.createUsersToSetToAProyect();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		// ojo que no se puede eliminar primero el usuario!
		// no es necesario eliminar la relacion usuarios-proyecto, se eliminana
		// en cascada.
		this.deleteCreatedProyect();
		this.deleteCreatedUserProyectLeader();
		this.deleteUsersSettedOnAProyect();
	}

	protected void createUsersToSetToAProyect() {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aCreatedUserDTO;
			try {
				aCreatedUserDTO = this.userService.createUser(this.sessionToken,
						TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i, " no importa");
				usuariosAAsignarAProyecto.add(aCreatedUserDTO);
			} catch (UserAlreadyExistsException e) {
				fail("El usuario que se intenta crear " + (TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i)
						+ " ya existe.");
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteUsersSettedOnAProyect() {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aUserDTOToRemove;
			try {
				aUserDTOToRemove = this.userService.getUserByUserName(this.sessionToken,
						TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i);
				this.userService.removeUser(this.sessionToken, aUserDTOToRemove);
			} catch (UnknownUserException e) {
				fail("El usuario " + TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + "_" + i + " no existe.");
			} catch (DTOConcurrencyException e) {
				fail("Error de concurrencia de DTO: Esto no deberia pasar ya que es un test controlado.");
			}
		}
	}

	@Test
	public void testUpdateProyect() {
		this.aCreatedProyectDTO.setUsers(usuariosAAsignarAProyecto);
		this.aCreatedProyectDTO.setName(TestConstants.UPDATED_PROYECT_NAME);
		try {
			this.proyectService.updateProyect(this.sessionToken, this.aCreatedProyectDTO);
		} catch (UnknownProjectException e) {
			fail("El proyecto que se quiere modificar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("Error de concurrencia de DTO: Esto no deberia pasar ya que es un test controlado.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios no existe.");
		}
		this.assertsOnUpdateProyectService();
	}

	private void assertsOnUpdateProyectService() {

		ProjectDTO anUpdatedProyectDTO = null;

		try {
			anUpdatedProyectDTO = this.proyectService.getProyect(this.sessionToken, this.aCreatedProyectDTO);
		} catch (UnknownProjectException e) {
			fail("No deberia pasar esto!");
		}

		assertEquals(this.aCreatedProyectDTO.getName(), anUpdatedProyectDTO.getName());
		assertNotSame(this.aCreatedProyectDTO.getVersion(), anUpdatedProyectDTO.getVersion());

		boolean existe = false;
		for (UserDTOForLists userDTOForList : this.aCreatedProyectDTO.getUsers()) {
			existe = false;
			for (UserDTOForLists userDTOForListOnUpdateProyect : anUpdatedProyectDTO.getUsers()) {
				if (userDTOForList.equals(userDTOForListOnUpdateProyect)) {
					existe = true;
					break;
				}
			}
			assertTrue(existe);
		}
	}
}