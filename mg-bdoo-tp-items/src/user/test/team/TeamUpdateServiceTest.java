package user.test.team;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;

import user.dto.UserDTO;
import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.exception.team.TeamAlreadyExistsException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamUpdateServiceTest extends TeamServiceTest {

	protected TeamDTO aCreatedTeamDTO;
	protected Collection<UserDTO> usersAsignedToATeam = new HashSet<UserDTO>();

	@Override
	@Before
	public void setUp() throws Exception {

		super.setUp();
		this.createUsersToSetToATeam();

	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.deleteCreatedTeam();
		this.deleteUsersSettedOnAProyect();
	}

	public void testCreateTeam() {
		try {
			this.aCreatedTeamDTO = this.teamService.createTeam(this.sessionToken, TestConstants.NEW_TEAM_NAME,
					usersAsignedToATeam);
		} catch (TeamAlreadyExistsException e) {
			fail("El equipo que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios que se quieren setear no existe.");
		}
	}

	private void deleteCreatedTeam() {
		// TODO Auto-generated method stub

	}

	protected void createUsersToSetToATeam() {
		for (int i = 0; i < TestConstants.AMOUNT_OF_USERS_TO_SET; i++) {
			UserDTO aCreatedUserDTO;
			try {
				aCreatedUserDTO = this.userService.createUser(this.sessionToken,
						TestConstants.BASE_USERS_NAME_TO_SET_IN_COLLECTION + i, " no importa");
				usersAsignedToATeam.add(aCreatedUserDTO);
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
}