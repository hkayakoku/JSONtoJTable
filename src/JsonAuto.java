

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonAuto {

	public String[] getObjectNames() {
		return ObjectNames;
	}




	private String URL;
	private String [] ObjectNames;
	private JSONArray jArray = null;
    private	JSONObject jsonObject = null;

	
	public void setURL(String URL) {

		this.URL = URL;

	}
	
	public boolean checkIfValid(String uRL) {
		JSONObject json = null;
		
//		System.out.println(uRL.lastIndexOf("http"));
//		System.out.println(uRL);

		try {
			json = readJsonFromUrl(uRL);
		} catch (IOException e) {
			System.out.println("IOException occured");
			e.printStackTrace();
			return false;
		} catch (JSONException e) {
			System.out.println("JSONException occured");
			e.printStackTrace();
			return false;
			}

		System.out.println(json.toString());
		
		return true;
	}
	

	public String[][] parseAndAssignTwoDimentional() {
		JSONObject json = null;

		try {
			json = readJsonFromUrl(URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ObjectNames = findObjectName(json);
		
		
		try {
			jArray = json.getJSONArray(ObjectNames[0]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jsonObject = jArray.getJSONObject(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		ObjectNames = findObjectName(jsonObject);
		
//		System.out.println(ObjectNames[0]);
		
		
		 List<String> list_data = new ArrayList<String>();
		
		  for (int i = 0; i < jArray.length(); i++) {
			
			  try {
				jsonObject = jArray.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  for (int j = 0; j < ObjectNames.length; j++) {
				
//				  System.out.println("jObj:" + jsonObject.get(TAGNAME[j]));
				  try {
					list_data.add(jsonObject.get(ObjectNames[j]).toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
		  
		
		  String [] allData;
		  allData = new String[list_data.size()];
		  list_data.toArray(allData);
		  
		
		return convertsTwoDimentional (allData,ObjectNames);
	}

	private String [] findObjectName(JSONObject json) {

		String [] keys;
		  List<String> list_key = new ArrayList<String>();
		  
		  String objectname = null;
		  Iterator iterator = json.keys();
		  while(iterator.hasNext())
		  {
			  String key = (String) iterator.next();
			  System.out.println("keys: "+key);
			  list_key.add(key);					  
		  }
		  Collections.sort(list_key);
		  keys = new String[list_key.size()];
		  list_key.toArray(keys);
		  
		return keys;
	}

	private JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		
		InputStream is;
		if(!isURL(url))
		{
			File file = new File(url);
			is = new FileInputStream(file);
		}
		else
		{
			is = new URL(url).openStream();
		}
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private boolean isURL(String url) {
		
		if (url.lastIndexOf("http://") < 0 )
		{
			System.out.println("it is not url !!!" );
			return false;
		}
		else
		{
			return true;
		}
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	
	private static String[][] convertsTwoDimentional(String[] allData,String[] tAGNAME) {
		
		int numOfTag = tAGNAME.length; // 3
		int numOfBlock = allData.length / numOfTag; // 5
		int counter = 0;
		String[][] Data = new String[numOfBlock][numOfTag];

		for (int i = 0; i < numOfBlock; i++) {
			for (int j = 0; j < numOfTag; j++) {
				Data[i][j] = allData[counter];
				counter++;
			}
		}
		return Data;
}
	
	
}
