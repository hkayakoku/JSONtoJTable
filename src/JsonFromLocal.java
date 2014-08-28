import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonFromLocal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//
		String path = "C:\\Users\\Hakan\\Desktop\\hadi.json";
		
		JSONObject json = null;
		try {
			json = readJsonFromLocal(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(json.toString());
		
	}

	private static JSONObject readJsonFromLocal(String path) throws IOException,
			JSONException {
		File file = new File(path);
		
		InputStream is = new FileInputStream(file);
//		InputStream is = new URL(path).openStream();
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
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}
