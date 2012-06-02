package user.test;

import java.util.HashSet;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import base.exception.DTOConcurrencyException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserUpdateServiceTest extends UserServiceTest {

	protected UserDTO anCreatedUserDTO;
	private final static int MASSIVE_AMOUNT = 50;

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		// se crea un usuario para probar el update comun.
		try {
			this.anCreatedUserDTO = this.userService.createUser("userUpdateTest", "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		// Se optienen de nuevo los usuarios porque se modificaron..
		UserDTO anUserDTOToRemove = this.userService.getUser(this.anCreatedUserDTO);

		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		this.userService.removeUser(anUserDTOToRemove);
	}

	@Test
	public void testUpdatePasswordUser() {
		// se updetean los campos necearios para la prueba
		this.anCreatedUserDTO.setPassword("password2");

		// Se updatea el usuario
		try {
			this.userService.updateUser(this.anCreatedUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta editar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El usuario que se intenta editar fue modificado por otro usuario, no puede pasar en este test.");
		}

		// Se recupera el usuario y se compara el valor que se intento guardar
		// con el recuperado.
		UserDTO aRetrivedUserDTO = null;
		try {
			aRetrivedUserDTO = this.userService.getUser(this.anCreatedUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que edito no se puede recuperar, no puede pasar en este test.");
		}
		// el valor updatiado y el valor recuperado deben ser iguales.
		assertNotNull(aRetrivedUserDTO);
		assertEquals(this.anCreatedUserDTO.getPassword(), aRetrivedUserDTO.getPassword());
	}

	@Test
	public void testUserUpdateMassiveConcurrencyTest() {

		Stack<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsStackNotFinisehd = new Stack<UserUpdateConcurrencyTest>();
		HashSet<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsSetForCheckConcurrency = new HashSet<UserUpdateConcurrencyTest>();
		UserDTO userDTOForMassiveTestAux = null;

		for (int i = 0; i < MASSIVE_AMOUNT; i++) {

			try {
				userDTOForMassiveTestAux = this.userService.getUser(this.anCreatedUserDTO);
			} catch (UnknownUserException e) {
				fail("El usuario que se intenta editar no existe.");
			}
			// modificamos los DTOs obtenidos.
			userDTOForMassiveTestAux.setPassword("password_for_massive_test_" + i);
			UserUpdateConcurrencyTest userUpdateConcurrencyTestAux = new UserUpdateConcurrencyTest(this.userService,
					userDTOForMassiveTestAux, "Massive Test : -" + i + "-");
			massiveUserUpdateConcurrencyTestsStackNotFinisehd.push(userUpdateConcurrencyTestAux);
			massiveUserUpdateConcurrencyTestsSetForCheckConcurrency.add(userUpdateConcurrencyTestAux);
		}

		for (UserUpdateConcurrencyTest userUpdateConcurrencyTest : massiveUserUpdateConcurrencyTestsStackNotFinisehd) {
			userUpdateConcurrencyTest.start();
		}

		// Esperamos que todos los Threads terminen..
		while (!massiveUserUpdateConcurrencyTestsStackNotFinisehd.empty()) {
			UserUpdateConcurrencyTest userUpdateConcurrencyTest = massiveUserUpdateConcurrencyTestsStackNotFinisehd
					.peek();
			if (userUpdateConcurrencyTest.isThreadFinished()) {
				massiveUserUpdateConcurrencyTestsStackNotFinisehd.pop();
			} else {
				try {
					Thread.sleep(1000); // un segundo
				} catch (InterruptedException e) {
					fail("Error en la prueba concurrente");
				}
			}
		}

		// Chequeamos que al menos falle
		boolean errorDeConcurrencia = false;
		for (UserUpdateConcurrencyTest userUpdateConcurrencyTest : massiveUserUpdateConcurrencyTestsSetForCheckConcurrency) {
			if (userUpdateConcurrencyTest.isConcurrencyError()) {
				errorDeConcurrencia = true;
				break;
			}
		}

		// El test no falla si hubo error de concurrencia de hibernate.
		assertTrue(errorDeConcurrencia);
	}

	@Test
	// Si se configura el servicio update con una determinada secuencia, se
	// puede establecer que usuario se guardo.
	// sin embargo hay que meterse en el servicio y queda feo. Por ahora esto no
	// se usa.
	public void porAhoraNoSeUsatestUserUpdateControledConcurrencyTest() {

		// Obtengo 3 veces el mismo usuario, no alcanza con usar el mismo DTO
		// porque se necesitan 3 DTOs diferentes para sumular 3 operaciones
		// simultaneas.
		UserDTO userDTOForPepeTest = null;
		UserDTO userDTOForFulanoTest = null;
		UserDTO userDTOForMenganoTest = null;
		try {
			userDTOForPepeTest = this.userService.getUser(this.anCreatedUserDTO);
			userDTOForFulanoTest = this.userService.getUser(this.anCreatedUserDTO);
			userDTOForMenganoTest = this.userService.getUser(this.anCreatedUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta editar no existe.");
		}

		// modificamos los DTOs obtenidos.
		userDTOForPepeTest.setPassword("password_pepe");
		userDTOForFulanoTest.setPassword("password_fulano");
		userDTOForMenganoTest.setPassword("password_mengano");

		// los hilos de ejecucion que simulan las operaciones.
		UserUpdateConcurrencyTest userUpdateConcurrencyTestPepe = new UserUpdateConcurrencyTest(this.userService,
				userDTOForPepeTest, "Transaccion PEPE");
		UserUpdateConcurrencyTest userUpdateConcurrencyTestFulano = new UserUpdateConcurrencyTest(this.userService,
				userDTOForFulanoTest, "Transaccion FULANO");
		UserUpdateConcurrencyTest userUpdateConcurrencyTestMengano = new UserUpdateConcurrencyTest(this.userService,
				userDTOForMenganoTest, "Transaccion MENGANO");

		userUpdateConcurrencyTestPepe.start();
		userUpdateConcurrencyTestFulano.start();
		userUpdateConcurrencyTestMengano.start();

		while (!userUpdateConcurrencyTestPepe.isThreadFinished() && !userUpdateConcurrencyTestFulano.isThreadFinished()
				&& !userUpdateConcurrencyTestMengano.isThreadFinished()) {
			try {
				Thread.sleep(1000); // un segundo
			} catch (InterruptedException e) {
				fail("Error en la prueba concurrente");
			}
		}
		// El test falla si no hubo error de concurrencia.
		boolean errorDeConcurrencia = (userUpdateConcurrencyTestPepe.isConcurrencyError()
				|| userUpdateConcurrencyTestFulano.isConcurrencyError() || userUpdateConcurrencyTestMengano
				.isConcurrencyError());

		// Se recupera el usuario y se compara el valor que se intento guardar
		// con el recuperado.
		UserDTO aRetrivedUserDTO = null;
		try {
			aRetrivedUserDTO = this.userService.getUser(this.anCreatedUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que edito no se puede recuperar, no puede pasar en este test.");
		}
		assertTrue(errorDeConcurrencia);
		// el valor updatiado y el valor recuperado deben ser iguales.
		assertEquals(userDTOForMenganoTest.getPassword(), aRetrivedUserDTO.getPassword());

	}
}