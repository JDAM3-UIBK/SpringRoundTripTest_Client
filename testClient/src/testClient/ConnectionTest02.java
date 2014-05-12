package testClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class ConnectionTest02 {
	
	//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-requestbody
	//http://www.codejava.net/frameworks/spring/spring-mvc-sample-application-for-downloading-files
	//http://www.veereshr.com/Android/AndroidToServlet
	
	public static  void main(String[] args) {
		// TODO Auto-generated method stub
		CloseableHttpClient hc = HttpClientBuilder.create().build();
		ResponseHandler <String> res = new BasicResponseHandler();  
		
		String postMessage = "jr in Json Test";
		
		HttpPost postMethod=new HttpPost("http://localhost:8080/logs");  
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1); 
		
		nameValuePairs.add(new BasicNameValuePair("json", postMessage));
		
		try {
			postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			String tmp = hc.execute(postMethod,res);
			
			
			System.out.println(tmp);
			
			/*
			 * HttpResponse response = client.execute(post);
		      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		      String line = "";
		      while ((line = rd.readLine()) != null) {
		        System.out.println(line);
		      }*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				hc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
