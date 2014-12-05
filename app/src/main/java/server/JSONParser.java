package server;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Braden on 11/30/2014.
 */
public class JSONParser {

    static InputStream is = null;
    static Object json = null;
    static String output = "";

    // constructor
    public JSONParser() {

    }

    public static Object getJSONFromUrl(String _url, JSONObject obj) {

        output = "";
        json = null;
        is = null;

        try {
            if (obj != null) {
                URL url = new URL(_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");

                OutputStream os = conn.getOutputStream();

                String input = obj.toString();

                os.write(input.getBytes());
                os.flush();
                if (conn.getResponseCode() != 200) {
                    BufferedReader error = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

                    //Log.e("connHTTPerror", error.toString());
                    String line = "";
                    Log.e("connHTTPerror", _url);
                    while ((line = error.readLine()) != null) Log.e("connHTTPerror", line + " " + conn.getResponseCode());
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String temp = "";
                while ((temp = br.readLine()) != null)
                    output += temp;
                //System.out.println(output);

                br.close();

                conn.disconnect();
            } else {
                try {
                    json = null;
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpPost = new HttpGet(_url);

                    BasicHttpParams par = new BasicHttpParams();
                    httpPost.setParams(par);
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();

                } catch (UnsupportedEncodingException e) {
                    //        e.printStackTrace();
                } catch (ClientProtocolException e) {
                    //      e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            is, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    output = sb.toString();
                } catch (Exception e) {
                    Log.e("Buffer Error", "Error converting result " + e.toString());
                }

            }

        } catch (
                MalformedURLException e
                )

        {
            //  e.printStackTrace();
        } catch (
                IOException e
                )

        {
            e.printStackTrace();
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }

        try
        {
            json = null;
            json = new JSONObject(output);
        } catch ( Exception e) {
            //Log.e("jsonparser", e.getMessage());
            try {
                json = new JSONArray(output);
            } catch(Exception g) {  }
        }
        return json;
    }
}
