package br.com.artit.rango;

import android.util.Log;
import org.json.JSONArray;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService {

    public JSONArray get(String pUrlApi) {

        JSONArray result = null;
        try {
            URL url = new URL(pUrlApi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches (false);

            //System.out.println("Response Code: " + conn.getResponseCode());
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String getResponse = convertStreamToStringSimple(in).trim();

            getResponse = getResponse.substring(getResponse.indexOf("["), getResponse.lastIndexOf("]") + 1);
            result = new JSONArray(getResponse);
            connection.disconnect();

        } catch (Throwable t) {
            Log.i("ERRO", "Request failed: " + t.toString());
        }
        return result;
    }


	//To convert Input Stream to String
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			is.close();
		} catch (OutOfMemoryError om) {
			om.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

    public static String convertStreamToStringSimple(InputStream is) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            result = reader.readLine();
            is.close();
        } catch (OutOfMemoryError om) {
            om.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
