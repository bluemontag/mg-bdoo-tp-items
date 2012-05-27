package base.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import base.contant.BaseContants;

import user.test.UserUpdateServiceTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class BaseSuiteTest {

	@SuppressWarnings("rawtypes")
	public static Test suite() {
		
		loadApplicationContext();

		Class[] testClasses = getTestsToPerform();
		TestSuite suite= new TestSuite(testClasses);

		return suite;
	}

	private static void loadApplicationContext() {
		String[] contextPaths = new String[] { BaseContants.CONTEXT_FILE };
		new ClassPathXmlApplicationContext(contextPaths);
	}

	@SuppressWarnings("rawtypes")
	protected static Class[] getTestsToPerform(){
		Class[] testClasses = {UserUpdateServiceTest.class};
		return testClasses;
	}
	
}
