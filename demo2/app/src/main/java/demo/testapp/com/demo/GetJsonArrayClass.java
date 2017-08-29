package demo.testapp.com.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

/**
 * Get Json Array from API URL
 * @author {Samsad Ahmad}
 *
 */
public class GetJsonArrayClass {

	  public  static  JSONArray  getJSONArrayfromURL(String url) 
	    {
	        InputStream mIs = null;
	        String result = "";
	        JSONArray jArrayEvent = null;
			StringBuilder stringBuilder;
			try {
				URL url1 = new URL(url);
				HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
				urlConnection.connect();
				try {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
					stringBuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line).append("\n");
					}
					bufferedReader.close();
					jArrayEvent = new JSONArray(stringBuilder.toString());
				}
				catch (Exception ex){
					Log.e("ERROR", ex.getMessage(), ex);
				}
				finally{
					urlConnection.disconnect();
				}
			}
			catch(Exception e) {
				Log.e("ERROR", e.getMessage(), e);
				return null;
			}
	        return jArrayEvent;
	    }
}