package user.test;


import java.util.Collection;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.dto.UserDTO;

import base.service.ServiceFinder;

public class UserTest extends TestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		ServiceFinder.getInstance().getUserService().removeUserByName("rodrigo1");
	}
	
	@Test
	public void testCreateUser(){
		ServiceFinder.getInstance().getUserService().createUser("rodrigo1", "rodrigo1");
		assertEquals("","");
	}
	
	@Test
	public void testListUsers(){
		Collection<UserDTO> users = ServiceFinder.getInstance().getUserService().listUsers();
		assertEquals("","");
	}

}
