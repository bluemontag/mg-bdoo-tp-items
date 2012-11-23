package user.test;

import java.util.HashSet;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class UserUpdateServiceTest extends UserServiceTest {

	private UserDTO anUserDTO;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.anUserDTO = this.createUser(TestConstants.NEW_USER_NAME, TestConstants.USER_PASSWORD);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.deleteUser(this.anUserDTO);
	}

	@Test
	public void testUpdatePasswordUser() {
		// se updetean los campos necearios para la prueba
		this.anUserDTO.setPassword("updatedPassword");

		// Se updatea el usuario
		try {
			this.userService.updateUser(this.sessionToken, this.anUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta editar no existe.");
		} catch (DTOConcurrencyException e) {
			fail("El usuario que se intenta editar fue modificado por otro usuario, no puede pasar en este test.");
		}
		assertUpdateTest();
	}

	private void assertUpdateTest() {
		// Se recupera el usuario y se compara el valor que se intento guardar
		// con el recuperado.
		UserDTO aRetrivedUserDTO = null;
		try {
			aRetrivedUserDTO = this.userService.getUser(this.sessionToken, this.anUserDTO);
		} catch (UnknownUserException e) {
			fail("El usuario que edito no se puede recuperar, no puede pasar en este test.");
		}
		// el valor updatiado y el valor recuperado deben ser iguales.
		assertNotNull(aRetrivedUserDTO);
		assertEquals(this.anUserDTO.getPassword(), aRetrivedUserDTO.getPassword());
	}

	@Test
	public void testUserUpdateMassiveConcurrencyTest() {

		// Se completa con todos los threads que se ejecutan y se van
		// desencolando cuando terminan.
		Stack<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsStackNotFinisehd = new Stack<UserUpdateConcurrencyTest>();

		HashSet<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsSetForCheckConcurrency = new HashSet<UserUpdateConcurrencyTest>();
		UserDTO userDTOForMassiveTestAux = null;

		for (int i = 0; i < TestConstants.MASSIVE_AMOUNT; i++) {

			try {
				userDTOForMassiveTestAux = this.userService.getUser(this.sessionToken, this.anUserDTO);
			} catch (UnknownUserException e) {
				fail("El usuario que se intenta editar no existe.");
			}
			// modificamos los DTOs obtenidos.
			userDTOForMassiveTestAux.setPassword("password_for_massive_test_" + i);
			UserUpdateConcurrencyTest userUpdateConcurrencyTestAux = new UserUpdateConcurrencyTest(this.sessionToken,
					this.userService, userDTOForMassiveTestAux, "Massive Test : -" + i + "-");
			massiveUserUpdateConcurrencyTestsStackNotFinisehd.push(userUpdateConcurrencyTestAux);
			massiveUserUpdateConcurrencyTestsSetForCheckConcurrency.add(userUpdateConcurrencyTestAux);
		}

		this.runAndWaitThreadsToFinish(massiveUserUpdateConcurrencyTestsStackNotFinisehd);

		boolean errorDeConcurrencia = false;
		errorDeConcurrencia = this
				.checkThreadThrowsConcurrencyError(massiveUserUpdateConcurrencyTestsSetForCheckConcurrency);

		// El test no falla si hubo error de concurrencia de hibernate.
		assertTrue(errorDeConcurrencia);
	}

	protected void runAndWaitThreadsToFinish(
			Stack<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsStackNotFinisehd) {

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
	}

	protected boolean checkThreadThrowsConcurrencyError(
			HashSet<UserUpdateConcurrencyTest> massiveUserUpdateConcurrencyTestsSetForCheckConcurrency) {
		// Chequeamos que al menos falle
		boolean errorDeConcurrencia = false;
		for (UserUpdateConcurrencyTest userUpdateConcurrencyTest : massiveUserUpdateConcurrencyTestsSetForCheckConcurrency) {
			if (userUpdateConcurrencyTest.isConcurrencyError()) {
				errorDeConcurrencia = true;
				break;
			}
		}
		return errorDeConcurrencia;
	}
}