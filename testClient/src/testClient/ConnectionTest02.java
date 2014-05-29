package testClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
		//CloseableHttpClient hc = HttpClientBuilder.create().build();
		//ResponseHandler <String> res = new BasicResponseHandler();  
		final HttpClient httpClient = new HttpClient();
		
		String postMessage = "jr in Json Test";
		User user = new User("hans12", "342");
		
		String jsonString = user.toJson();
		
		try {
			
			
			StringRequestEntity requestEntity = new StringRequestEntity(jsonString,
				    "application/json",
				    "UTF-8");
			
			
			System.out.println(requestEntity.toString());
			PostMethod postMethod = new PostMethod("http://localhost:8080/cp418/usermanagement");
			postMethod.setRequestEntity(requestEntity);
			int status = httpClient.executeMethod(postMethod);
			
			System.out.println("status: " + status);
			//getResponseBodyAsStream
			String tmp = postMethod.getResponseBodyAsString();
			System.out.println("responseBody: " + tmp);
			postMethod.releaseConnection();
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		HttpPost postMethod=new HttpPost("http://localhost:8080/cp418/usermanagement/createPost");  
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1); 
		
		
		nameValuePairs.add(new BasicNameValuePair("json","test"));
		
		
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
		/*	
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
		*/ catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
