package testClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class CompareConnectionManager {
	
	//User user = new User("hansjochen99", "34224");
	
	public static String createUser(String username, String password){
		User user = new User(username, password);
		
		String jsonString = user.toJson();
		
		return jsonString;
	}
	public static String createRoute(String userName,int duration, int length, String type, double CO2, double costs, Date date,
            int referencelength, double referenceco2, double referencecosts){
		LoggedRoute user = new LoggedRoute(userName, duration, length,  type, CO2,  costs, date,
	            referencelength,  referenceco2, referencecosts);
		
		String jsonString = user.toJson();
		
		return jsonString;
	}
	
	public static int connection( final String jsonString, final String url){
		
		PostMethod postMethod = null;
		HttpClient httpClient = new HttpClient();
		//HostConfiguration hf=new HostConfiguration();
		//hf.setHost("http://localhost", 8080);
		
		
		int status = 0;
		try {
			StringRequestEntity requestEntity = new StringRequestEntity(jsonString,
				    "application/json",
				    "UTF-8");
			
			//87.106.189.151
			//http://87.106.189.151:8080/cp422/usermanagement
			postMethod = new PostMethod(url);
			//postMethod.setHostConfiguration(hf);
			postMethod.setRequestEntity(requestEntity);
			
			//System.out.println(requestEntity.toString());
			
			status = httpClient.executeMethod(postMethod);
			
			//System.out.println("status: " + status);
			
			String tmp = postMethod.getResponseBodyAsString();
			//System.out.println("responseBody: " + tmp);
			
			postMethod.releaseConnection();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
	}
	public static int connectionGET(final String url){
		GetMethod getMethod = null;
		HttpClient httpClient = new HttpClient();
		
		
		int status = 0;
		try {
			
			//87.106.189.151
			//http://87.106.189.151:8080/cp422/usermanagement
			getMethod = new GetMethod(url);
		
			
			status = httpClient.executeMethod(getMethod);
			
			System.out.println("status: " + status);
			
			String tmp = getMethod.getResponseBodyAsString();
			System.out.println("responseBody: " + tmp);
			
			getMethod.releaseConnection();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return status;
	}
public static int connectionWithString( final String str, final String url){
		
		PostMethod postMethod = null;
		HttpClient httpClient = new HttpClient();
		//HostConfiguration hf=new HostConfiguration();
		//hf.setHost("http://localhost", 8080);
		
		
		int status = 0;
		try {
			
			
			//87.106.189.151
			//http://87.106.189.151:8080/cp422/usermanagement
			postMethod = new PostMethod(url);
			//postMethod.setHostConfiguration(hf);
			StringRequestEntity requestEntity = new StringRequestEntity(str);
			System.out.println(requestEntity.toString());
			postMethod.setRequestEntity(requestEntity);
			
			
			status = httpClient.executeMethod(postMethod);
			
			System.out.println("status: " + status);
			
			String tmp = postMethod.getResponseBodyAsString();
			System.out.println("responseBody: " + tmp);
			
			postMethod.releaseConnection();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
	}
}
