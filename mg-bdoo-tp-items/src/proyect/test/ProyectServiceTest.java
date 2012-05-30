package proyect.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;
import proyect.exception.UnknownProyectException;
import proyect.service.ProyectServiceBI;
import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.service.UserServiceBI;
import base.exception.DTOConcurrencyException;
import base.service.ServiceFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class ProyectServiceTest extends TestCase {

	protected ProyectDTO aCreatedProyectDTO;
	protected UserDTO aCreatedProyectLeaderUserDTO;

	protected ProyectServiceBI proyectService;
	protected UserServiceBI userService;

	protected final static String NEW_PROYECT_NAME = "A proyect name";
	protected final static String NEW_USER_NAME = "userProyectLeaderTest";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	@Before
	public void setUp() throws Exception {
		this.proyectService = ServiceFinder.getInstance().getProyectService();
		this.userService = ServiceFinder.getInstance().getUserService();
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	protected void createProyect() {
		try {
			// se crea el proyecto que se va a updetear
			this.aCreatedProyectDTO = this.proyectService.createProyect(NEW_PROYECT_NAME,
					this.aCreatedProyectLeaderUserDTO);
		} catch (ProyectAlreadyExistsException e) {
			fail("El proyecto que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta asignar como lider no existe.");
		}
	}

	protected void createUserProyectLeader() {
		try {
			// se crea un usuario para setear como lider del proyecto.
			this.aCreatedProyectLeaderUserDTO = this.userService.createUser(NEW_USER_NAME, "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	protected void deleteCreatedUserProyectLeader() throws UnknownUserException, DTOConcurrencyException {
		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		UserDTO anUserDTOToRemove = this.userService.getUser(this.aCreatedProyectLeaderUserDTO);
		this.userService.removeUser(anUserDTOToRemove);
	}

	protected void deleteCreatedProyect() throws UnknownProyectException, DTOConcurrencyException {
		ProyectDTO aProyectDTOToRemove = this.proyectService.getProyect(this.aCreatedProyectDTO);
		// Se elimina el proyecto para dejar la base como estaba.
		this.proyectService.removeProyect(aProyectDTOToRemove);
	}
}
