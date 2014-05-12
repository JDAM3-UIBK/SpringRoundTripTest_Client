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
		  Integer doubledValue =5;
		
		try {
			URL url = new URL("http://localhost:8080/gradle-test-0.1.0");
			URLConnection connection = url.openConnection();
			
			//inputString
			String input = "test-from-client";
			
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(input);
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
            String returnString="";
            
            while ((returnString = in.readLine()) != null) 
            {
                doubledValue= Integer.parseInt(returnString);
            }
            in.close();
            
            System.out.println(doubledValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
