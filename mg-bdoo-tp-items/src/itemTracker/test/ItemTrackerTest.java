package itemTracker.test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import base.test.BaseTestCase;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTrackerTest extends BaseTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("rawtypes")
	public static Collection<Class> getClassesTestToPerform() {
		// en este metodo se agregan todos los test a realizar relacionados
		Collection<Class> itemTrackerTestsClasses = new ArrayList<Class>();
		itemTrackerTestsClasses.add(ItemTrackerBigTest.class);
		return itemTrackerTestsClasses;
	}
}
