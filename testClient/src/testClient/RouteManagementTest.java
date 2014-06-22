package testClient;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RouteManagementTest {

	private static String hostroute;
	
	private String testRoute;
	private String testRoute1;
	private String testRoute2;
	private String username;
	
	@BeforeClass
	public static void setUpBeforClass(){
		hostroute = "http://localhost:8080/cp423/routemanagement/";
		
	}
	
	@Before
	public void setUp() throws Exception {
		
		
		Date date = new Date(23424);
		
		List<LoggedRoute> list = new ArrayList<LoggedRoute>();
		list.add(new LoggedRoute("ranger1966", 234 ,234, "type", 324, 112, date, 24342, 324, 123));
		list.add(new LoggedRoute("ranger1966", 234 ,214, "type", 324, 122, date, 24342, 334, 123));
		
		this.testRoute = CompareConnectionManager.createRoute("hans1966", 234 ,234, "type", 324, 112, date, 24342, 324, 123);
		this.testRoute1 = CompareConnectionManager.createRoute("ranger1966", 234 ,234, "type", 324, 112, date, 24342, 324, 123);
		this.testRoute2 = CompareConnectionManager.createRoute("ranger1966", 256, 265, "type", 324, 112, date, 2242, 324, 123);
		
		
		this.username = "ranger1966";
		
		CompareConnectionManager.connection(testRoute1, hostroute + "saveRoute");
		CompareConnectionManager.connection(testRoute2, hostroute + "saveRoute");
	}

	@Test
	public void saveRouteTest() {
		int status;
		
		status = CompareConnectionManager.connection(testRoute, hostroute + "saveRoute");
		assertEquals(status, 200);
		
		//HttpStatus.BAD_REQUEST
		//status = CompareConnectionManager.connection(null, hostroute + "saveRoute");
		//assertEquals(status, 406);
	}
	
	@Test
	public void showRoutesTest(){
		int status;
		
		status = CompareConnectionManager.connection(testRoute, hostroute + "showRoutePerUser");
		assertEquals(status, 200);
		
	}
	
	@After
	public void tearDown() throws Exception {
		CompareConnectionManager.connection(testRoute, hostroute + "deleteRoute");
		CompareConnectionManager.connection(testRoute1, hostroute + "deleteRoute");
		CompareConnectionManager.connection(testRoute2, hostroute + "deleteRoute");
	}
}
