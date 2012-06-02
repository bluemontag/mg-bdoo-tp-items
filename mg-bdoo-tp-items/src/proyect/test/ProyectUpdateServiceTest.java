package proyect.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;

import proyect.exception.UnknownProyectException;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectUpdateServiceTest extends ProyectServiceTest {

	Collection<UserDTO> usuariosAAsignarAProyecto = new ArrayList<UserDTO>();
	protected static final int AMOUNT_OF_USERS_TO_SET_IN_PROYECT = 15;
	protected static final String BASE_USERS_NAME_TO_SET_IN_PROYECT = "user_setted_on_proyect_";
	protected static final String UPDATED_PROYECT_NAME = "A proyect name UPDATED";

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
		for (int i = 0; i < AMOUNT_OF_USERS_TO_SET_IN_PROYECT; i++) {
			UserDTO aCreatedUserDTO;
			try {
				aCreatedUserDTO = this.userService.createUser(BASE_USERS_NAME_TO_SET_IN_PROYECT + i, " no importa");
				usuariosAAsignarAProyecto.add(aCreatedUserDTO);
			} catch (UserAlreadyExistsException e) {
				fail("El usuario que se intenta crear " + (BASE_USERS_NAME_TO_SET_IN_PROYECT + i) + " ya existe.");
			}
		}
	}

	protected void deleteUsersSettedOnAProyect() {
		for (int i = 0; i < AMOUNT_OF_USERS_TO_SET_IN_PROYECT; i++) {
			UserDTO aUserDTOToRemove;
			try {
				aUserDTOToRemove = this.userService.getUserByUserName(BASE_USERS_NAME_TO_SET_IN_PROYECT + i);
				this.userService.removeUser(aUserDTOToRemove);
			} catch (UnknownUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DTOConcurrencyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void testUpdateProyect() {
		this.aCreatedProyectDTO.setUsers(usuariosAAsignarAProyecto);
		this.aCreatedProyectDTO.setName(UPDATED_PROYECT_NAME);
		try {
			this.proyectService.updateProyect(this.aCreatedProyectDTO);
		} catch (UnknownProyectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DTOConcurrencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}