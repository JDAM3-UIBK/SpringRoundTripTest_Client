package testClient;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;

public class UserManagementTest {

	private static String host; 
	
	private String userClient;
	private String userClient1;
	private String userClient2;
	private String userClient3;
	private String userClientDelete;
	
	@BeforeClass
	public static void setUpBeforClass(){
		 host  = "http://87.106.189.151:8080/cp423/usermanagement/";
	}
	
	@Before
	public void setUp() throws Exception {
		
		 
		 this.userClient = CompareConnectionManager.createUser("hans1966", "23453sf");
		 
		 this.userClientDelete = CompareConnectionManager.createUser("ranger1966", "23453sf");
		 
		 this.userClient1 = CompareConnectionManager.createUser("ranger1966", "23453sf");
		 
		 //Test: wrong username
		 this.userClient2 = CompareConnectionManager.createUser("rangerWrong", "23453sf");
		//Test: wrong password
		 this.userClient3 = CompareConnectionManager.createUser("ranger1966", "WrongPassword");
		 
		 //User for loginUserTest
		 CompareConnectionManager.connection(userClient1, host + "Register");
		 
		 //User for deletUserTest
		 CompareConnectionManager.connection(userClientDelete, host + "Register");
		 
	}

	@Test
	public void deletUserTest(){
		int status;
			
		// Delete given User
		status = CompareConnectionManager.connection(userClientDelete, host + "Delete");
		assertEquals(status, 200);		
		//should return 404 user not found
		status = CompareConnectionManager.connection(userClientDelete, host + "Delete");
		assertEquals(status, 404);
	}
	@Test
	public void registerUserTest() {
		int status = 0;	
		
		//Sends User as Json to spring Controller and then save User to Database
		status = CompareConnectionManager.connection(userClient, host + "Register");
		
		assertEquals(status, 200);
		
		//should return 406, because User already registered
		status = CompareConnectionManager.connection(userClient, host + "Register");
		
		assertEquals(status, 406);
	}
	
	//setupBefore
	@Test
	public void loginUserTest() {
		int status;
		
		//Sends User as Json to spring Controller and checks, if User is in Database
		status = CompareConnectionManager.connection(userClient1, host + "Anmelden");
		assertEquals(status, 200);
		
		//user not saved in Database = wrong username
		status = CompareConnectionManager.connection(userClient2, host + "Anmelden");
		assertEquals(status, 404);
		
		// user with wrong password
		status = CompareConnectionManager.connection(userClient3, host + "Anmelden");
		assertEquals(status, 404);
	}
	
	@After
	public void tearDown() throws Exception {
		CompareConnectionManager.connection(userClient, host + "Delete");
		CompareConnectionManager.connection(userClient1, host + "Delete");
		
	}
}
