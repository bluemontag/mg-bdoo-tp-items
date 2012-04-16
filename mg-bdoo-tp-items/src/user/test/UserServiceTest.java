package user.test;


import java.util.Collection;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.dto.UserDTO;
import user.exception.UserAlreadyExistsException;
import user.service.UserServiceBI;

import base.service.ServiceFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public abstract class UserServiceTest extends TestCase{


	protected UserServiceBI userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.userService = ServiceFinder.getInstance().getUserService();
	}

	@After
	public void tearDown() throws Exception {
	}
}
