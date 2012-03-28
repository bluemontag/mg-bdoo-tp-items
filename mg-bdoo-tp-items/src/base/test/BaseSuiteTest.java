package base.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.test.UserTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public abstract class BaseSuiteTest {

	final static String CONTEXT = "applicationContext.xml";
	
	public static Test suite() {
		
		loadApplicationContext();
		
		Class[] testClasses = getTestsToPerform();
		TestSuite suite= new TestSuite(testClasses);

		return suite;
	}

	private static void loadApplicationContext() {
		String[] contextPaths = new String[] { CONTEXT };
		new ClassPathXmlApplicationContext(contextPaths);
	}

	protected static Class[] getTestsToPerform(){
		Class[] testClasses = {UserTest.class};
		return testClasses;
	}
	
}
