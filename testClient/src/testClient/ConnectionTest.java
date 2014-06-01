package testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String host = "http://87.106.189.151:8080/cp422/usermanagement/";
		
		//creates User on Client
		String userClient = CompareConnectionManager.createUser("MaxDoe1966", "23453sf");
		
		//Sends User as Json to spring Controller and then save User to Database
		CompareConnectionManager.connection(userClient, host + "Register");
		
		//Sends User as Json to spring Controller and checks, if User is in Database
		CompareConnectionManager.connection(userClient, host + "Anmelden");
		
		//Changes Passwort of given User
		//Checks, if old passwort is korrekt
		CompareConnectionManager.connection(userClient, host + "changePassword");
				
		
		// Delete given User
		CompareConnectionManager.connection(userClient, host + "Delete");
		
	}
	
}
