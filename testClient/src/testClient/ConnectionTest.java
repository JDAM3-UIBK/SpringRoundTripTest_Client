package testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConnectionTest {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String host = "http://87.106.189.151:8080/cp423/usermanagement/";
		
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
						
				
		// Delete given User
		//CompareConnectionManager.connection(userClient, host + "Delete");
				
		//should return 404 user not found
		//CompareConnectionManager.connection(userClient, host + "Delete");
		
		/*	Gewünschtes Ergebnis:
		 	org.apache.commons.httpclient.methods.StringRequestEntity@6659c656
			status: 200
			responseBody: {"user_name_id":"ranger1966","user_pw":"23453sf"}
			org.apache.commons.httpclient.methods.StringRequestEntity@2530c12
			status: 406
			responseBody:  User is already Registered! 
			org.apache.commons.httpclient.methods.StringRequestEntity@73c6c3b2
			status: 200
			responseBody: {"user_name_id":"ranger1966","user_pw":"23453sf"}
			org.apache.commons.httpclient.methods.StringRequestEntity@48533e64
			status: 404
			responseBody:  User Not Found! 
			org.apache.commons.httpclient.methods.StringRequestEntity@64a294a6
			status: 404
			responseBody:  Password or User wrong!! 
			org.apache.commons.httpclient.methods.StringRequestEntity@7e0b37bc
			status: 200
			responseBody: {"user_name_id":"ranger1966","user_pw":"23453sf"}
			org.apache.commons.httpclient.methods.StringRequestEntity@3b95a09c
			status: 404
			responseBody: User not Found
		*/
		System.out.println("!----------Route Test--------------!");
		
		String hostroute = "http://87.106.189.151:8080/cp423/routemanagement/";
		
		Date date = new Date(23424);
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
		//CompareConnectionManager.connectionGET("http://87.106.189.151:8080/cp423/routemanagement/findRouteWithId?id=2");
		
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
