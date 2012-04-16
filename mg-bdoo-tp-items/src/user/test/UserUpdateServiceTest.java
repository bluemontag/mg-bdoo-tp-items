package user.test;

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
public class UserUpdateServiceTest extends UserServiceTest{
	
	protected UserDTO anCreatedUserDTO;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		// se crea un usuario para probar el update.
		try {
			this.anCreatedUserDTO = this.userService.createUser("userUpdateTest", "password1");
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		}
	}

	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		// se optiene de nuevo el usuario porque se modifico..
		UserDTO anUserDTOToRemove = this.userService.getUser(this.anCreatedUserDTO);
		// Se elimina el usuario creado, hay que dejar la base como estaba.
		this.userService.removeUser(anUserDTOToRemove);
	}

	@Test
	public void testUpdatePasswordUser(){
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
		
		// Se recupera el usuario y se compara el valor que se intento guardar con el recuperado.
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
}
