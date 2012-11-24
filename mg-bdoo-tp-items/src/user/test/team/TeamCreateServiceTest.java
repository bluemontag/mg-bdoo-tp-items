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
	private String testCode;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		testCode = " " + TeamCreateServiceTest.class.getSimpleName();
		this.aUserDTOForListCollection = this.createAUserCollection(testCode);
	}

	@Override
	@After
	public void tearDown() throws Exception {
		this.removeTeam(this.aTeamDTO);
		this.removeTheUserCollection(testCode);
	}

	public void testCreateTeam() {
		this.aTeamDTO = this.createTeam(this.aUserDTOForListCollection, TestConstants.TEAM_NAME + testCode);
		assertEquals(this.aTeamDTO.getName(), TestConstants.TEAM_NAME + testCode);
	}
}