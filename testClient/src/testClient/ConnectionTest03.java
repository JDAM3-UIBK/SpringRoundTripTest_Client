package testClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionTest03 {

	
	public static  void main(String[] args) {
		User user = new User();
		HttpURLConnection urlCon;
		ObjectOutputStream out;
		URL url;
		try {
			url = new URL("http://localhost:8080/cp418/usermanagement");
			urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setRequestMethod("POST");
		    urlCon.setDoOutput(true); // to be able to write.
		    urlCon.setDoInput(true); // to be able to read.
		    

		    out = new ObjectOutputStream(urlCon.getOutputStream());
		    out.writeObject(user);
		    out.close();
		    
		    ObjectInputStream ois = new ObjectInputStream(urlCon.getInputStream());
		    user = (User) ois.readObject();
		    ois.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
