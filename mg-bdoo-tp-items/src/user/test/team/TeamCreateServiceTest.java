package user.test.team;

import org.junit.After;
import org.junit.Before;

import user.dto.team.TeamDTO;
import base.test.TestConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TeamCreateServiceTest extends TeamServiceTest {

	private TeamDTO aTeamDTO;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.aUserDTOForListCollection = this.createAUserCollection(TeamCreateServiceTest.class.toString());
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(TeamCreateServiceTest.class.toString());
	}

	public void testCreateTeam() {
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.NEW_TEAM_NAME);
		assertEquals(this.aTeamDTO.getName(), TestConstants.NEW_TEAM_NAME);
	}
}