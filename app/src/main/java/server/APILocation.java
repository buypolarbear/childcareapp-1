package server;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Braden on 12/9/2014.
 */
public class APILocation {

    public String baseUrl = "http://ziptasticapi.com/{id}";

    public JSONObject GetLocationByZip(String zip) {
        PerformQuery(baseUrl.replace("{id}", zip), null);
        try {
            return new JSONObject(response.toString());
        } catch(Exception e) { e.printStackTrace(); }

        return null;
    }

    Object response = null;
    ///////////////////////////////////////////////////////////////////////////////
    Lock lock = new ReentrantLock();
    ///////////////////////////////////////////////////////////////////////////////
    private void PerformQuery(String url, JSONObject args) {

        lock.lock();
        try {
            HttpAsyncObject obj = new HttpAsyncObject();
            obj.url = url;
            obj.args = args;
            new HttpAsyncTask().execute(obj);
        } catch (Exception e) {
            Log.e("PerformQuery lock", e.getMessage());
        } finally {
            try {
                while (done == false) //should block
                    Thread.sleep(1000);
            } catch(Exception e) { Log.e("waiting", e.getMessage()); }

            lock.unlock();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////
    boolean done = false;
    ///////////////////////////////////////////////////////////////////////////////
    private class HttpAsyncTask extends AsyncTask<HttpAsyncObject, Void, Object> {

        @Override
        protected Object doInBackground(HttpAsyncObject... httpAsyncObjects) {
            response = JSONParser.getJSONFromUrl(httpAsyncObjects[0].url, httpAsyncObjects[0].args);
            done = true;
            return response;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////
}
