package user.test.team;

import org.junit.After;
import org.junit.Before;

import user.dto.team.TeamDTO;
import user.exception.UnknownUserException;
import user.exception.team.TeamAlreadyExistsException;
import user.exception.team.UnknownTeamException;
import base.exception.DTOConcurrencyException;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamCreateServiceTest extends TeamServiceTest {

	protected TeamDTO aCreatedTeamDTO;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.deleteCreatedTeam();
		// this.deleteTheUserCollection();
	}

	public void testCreateTeam() {
		try {
			this.aCreatedTeamDTO = this.teamService.createTeam(this.sessionToken, TestConstants.NEW_TEAM_NAME,
					this.aUserDTOForListCollection);
		} catch (TeamAlreadyExistsException e) {
			fail("El equipo que se intenta crear ya existe.");
		} catch (UnknownUserException e) {
			fail("Alguno de los usuarios que se quieren setear no existe.");
		}
	}

	protected void deleteCreatedTeam() {
		try {
			TeamDTO aTeamDTO = this.teamService.getTeam(this.sessionToken, this.aCreatedTeamDTO);
			this.teamService.removeTeam(this.sessionToken, aTeamDTO);
		} catch (DTOConcurrencyException e) {
			fail("El equipo que intenta eliminar fue modificado por otro usuario.");
		} catch (UnknownTeamException e) {
			fail("El equipo que se desea eliminar no existe.");
		}
	}
}