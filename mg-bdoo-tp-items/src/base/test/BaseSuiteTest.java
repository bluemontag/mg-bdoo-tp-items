package base.test;

import itemTracker.test.ItemTrackerTest;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import project.test.ProjectServiceTest;
import user.test.UserServiceTest;
import user.test.team.TeamServiceTest;
import workflow.test.WorkflowServiceTest;
import workflow.test.state.ItemStateServiceTest;
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

	@SuppressWarnings({ "rawtypes" })
	protected static Class[] getTestsToPerform() {
		ArrayList<Class> testClasses = new ArrayList<Class>();
		testClasses.addAll(ItemTrackerTest.getClassesTestToPerform());
		testClasses.addAll(ProjectServiceTest.getClassesTestToPerform());
		// testClasses.addAll(ItemServiceTest.getClassesTestToPerform());
		// testClasses.addAll(ItemTypeServiceTest.getClassesTestToPerform());
		testClasses.addAll(UserServiceTest.getClassesTestToPerform());
		testClasses.addAll(TeamServiceTest.getClassesTestToPerform());
		testClasses.addAll(WorkflowServiceTest.getClassesTestToPerform());
		testClasses.addAll(ItemStateServiceTest.getClassesTestToPerform());
		// testClasses.addAll(TransitionServiceTest.getClassesTestToPerform());
		Class[] testClassesArray = testClasses.toArray(new Class[testClasses.size()]);
		return testClassesArray;
	}

}
