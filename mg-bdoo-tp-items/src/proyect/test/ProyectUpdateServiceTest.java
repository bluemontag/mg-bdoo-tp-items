package proyect.test;

import org.junit.After;
import org.junit.Before;

import proyect.dto.ProyectDTO;
import proyect.exception.ProyectAlreadyExistsException;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ProyectUpdateServiceTest extends ProyectServiceTest{
	
	@Before
	public void setUp() throws Exception{
		
		super.setUp();
		try {
			// se crea un usuario para setear como lider del proyecto.
			this.aCreatedProyectLeaderUserDTO = this.userService.createUser("userProyectLeaderTest", "password1");
			// se crea un proyect para probar el update comun.
			this.aCreatedProyectDTO = this.proyectService.createProyect("ProyectTest", this.aCreatedProyectLeaderUserDTO);
		} catch (UserAlreadyExistsException e) {
			fail("El usuario que se intenta crear ya existe.");
		} catch (ProyectAlreadyExistsException e) {
			fail("El proyect que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("El usuario que se intenta setear como lider no existe.");
		}
	}

	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {

		ProyectDTO anProyectDTOToRemove = this.proyectService.getProyect(this.aCreatedProyectDTO); 
		UserDTO anUserDTOToRemove = this.userService.getUser(this.aCreatedProyectLeaderUserDTO);
		
		// Se elimina el proyecto para dejar la base como estaba.
		this.proyectService.removeProyect(anProyectDTOToRemove);
		
		// Se eliminan los usuarios creados, hay que dejar la base como estaba.
		this.userService.removeUser(anUserDTOToRemove);
	}
	
	
}