package server;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.Person;

/**
 * Created by Braden on 11/29/2014.
 */
public class API {

    private String baseUrl = "http://localhost:27929/api/";
    private String peopleUrl = "people";
    private String personUrl = "people/{id}";

    ///////////////////////////////////////////////////////////////////////////////
    public Person[] GetPeople() {
        PerformQuery(baseUrl + peopleUrl, null); //should block until finished
        Person[] people = new Gson().fromJson(response.toString(), Person[].class);
        response = null;
        return people;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public Person GetPerson(int id) {
        PerformQuery(baseUrl + personUrl.replace("{id}", Integer.toString(id)), null); //should block until finished
        Person people = new Gson().fromJson(response.toString(), Person.class);
        response = null;
        return people;
    }
    ///////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////
    Object response = null;
    Lock lock = new ReentrantLock();
    ///////////////////////////////////////////////////////////////////////////////
    private void PerformQuery(String url, List<NameValuePair> args) {

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
