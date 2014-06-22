package testClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class ConnectionTestLocal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("!----------User Test--------------!");
		String host = "http://localhost:8080/cp424/usermanagement/";
		String userClient6 = CompareConnectionManager.createUser("jochen", "23453sf");
		CompareConnectionManager.connection(userClient6, host + "Anmelden");
		
		
		//creates User on Client
		//right user
		String userClient = CompareConnectionManager.createUser("ranger1966", "23453sf");
		//user not saved in Database = wrong username
		String userClient2 = CompareConnectionManager.createUser("rangerWrong", "23453sf");
		// user with wrong password
		String userClient3 = CompareConnectionManager.createUser("ranger1966", "WrongPassword");
		
		//Sends User as Json to spring Controller and then save User to Database
		CompareConnectionManager.connection(userClient, host + "Register");
		
		//should return 406, because User already registered
		CompareConnectionManager.connection(userClient, host + "Register");
		
		
		//Sends User as Json to spring Controller and checks, if User is in Database
		CompareConnectionManager.connection(userClient, host + "Anmelden");

		//user not saved in Database = wrong username
		CompareConnectionManager.connection(userClient2, host + "Anmelden");
		
		// user with wrong password
		CompareConnectionManager.connection(userClient3, host + "Anmelden");
		
		//Changes Passwort of given User
		//Checks, if old passwort is korrekt
		//CompareConnectionManager.connection(userClient, host + "changePassword");
			
		
		
		System.out.println("!----------Route Test--------------!");
		
		String hostroute = "http://localhost:8080/cp424/routemanagement/";
		
		Date date = new Date(23123);
		System.out.println(date);
		
		List<LoggedRoute> list = new ArrayList<LoggedRoute>();
		
		list.add(new LoggedRoute("ranger1966", 234 ,234, "type", 324, 112, date, 24342, 324, 123));
		list.add(new LoggedRoute("ranger1966", 234 ,214, "type", 324, 122, date, 24342, 334, 123));
		
		System.out.println(LoggedRoute.toJsonArray(list));
		
		String testRoute = CompareConnectionManager.createRoute("ranger1966", 234 ,234, "type", 324, 112, date, 24342, 324, 123);
		String testRoute2 = CompareConnectionManager.createRoute("ranger1966", 256, 265, "type", 324, 112, date, 2242, 324, 123);
		String username = "ranger1966";
		
		System.out.println("Route auf Client Seite: " + testRoute);
		
		CompareConnectionManager.connection(testRoute, hostroute + "saveRoute");
		CompareConnectionManager.connection(testRoute2, hostroute + "saveRoute");
		
		CompareConnectionManager.connection(testRoute, hostroute + "showRoutePerUser");
		
		//Suche nach Route mit Id
		CompareConnectionManager.connectionGET("http://localhost:8080/cp423/routemanagement/findRouteWithId?id=22");
		
		//gibt alle Routen zurück, die angegebenen Usernamen zugeordnet sind
		CompareConnectionManager.connectionWithString(username, hostroute + "username");
		
		//Sollte error zurückliefern
		CompareConnectionManager.connection("wrongUser", hostroute + "username");
		
		CompareConnectionManager.connection(testRoute, hostroute + "deleteRoute");
		
		CompareConnectionManager.connection(testRoute2, hostroute + "deleteRoute");
		
		//CompareConnectionManager.connection(testRoute, hostroute + "deleteRoutesWithUsername");
		
		// Delete given User
		CompareConnectionManager.connection(userClient, host + "Delete");
				
		//should return 404 user not found
		CompareConnectionManager.connection(userClient, host + "Delete");
	}
	
}
