package user.test.team;

import org.junit.After;
import org.junit.Before;

import user.dto.team.TeamDTO;
import user.exception.team.UnknownTeamException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamCreateServiceTest extends TeamServiceTest {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.createAUserCollection();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.removeTeam();
		this.removeTheUserCollection();
	}

	public void testCreateTeam() {
		this.createTeam();
		TeamDTO aTeamDTO = null;
		try {
			aTeamDTO = this.teamService.getTeam(this.sessionToken, this.aTeamDTO);
		} catch (UnknownTeamException e) {
			fail("El team no existe");
		}
		assertEquals(this.aTeamDTO.getVersion(), aTeamDTO.getVersion());
	}

}