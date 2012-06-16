package base.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import project.test.ProjectCreateServiceTest;
import project.test.ProjectUpdateServiceTest;
import user.test.UserUpdateServiceTest;
import user.test.team.TeamUpdateServiceTest;
import base.contant.BaseConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseSuiteTest {

	@SuppressWarnings("rawtypes")
	public static Test suite() {

		loadApplicationContext();

		Class[] testClasses = getTestsToPerform();
		TestSuite suite = new TestSuite(testClasses);

		return suite;
	}

	private static void loadApplicationContext() {
		String[] contextPaths = new String[] { BaseConstants.CONTEXT_FILE };
		new ClassPathXmlApplicationContext(contextPaths);
	}

	@SuppressWarnings("rawtypes")
	protected static Class[] getTestsToPerform() {
		Class[] testClasses = { UserUpdateServiceTest.class, ProjectCreateServiceTest.class,
				ProjectUpdateServiceTest.class, TeamUpdateServiceTest.class };
		return testClasses;
	}

}
