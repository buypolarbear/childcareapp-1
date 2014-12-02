package server;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.Address;
import model.Availability;
import model.Event;
import model.Person;

/**
 * Created by Braden on 11/29/2014.
 */
public class API {

    private String baseUrl = "http://localhost:2786/api/"; //PORT MUST BE CONSTANT
    private String peopleUrl = "people";
    private String personUrl = "people/{id}";
    private String availabilityUrl = "availabilities/{id}";
    private String eventsUrl = "event";
    private String eventUrl = "event/{id}";
    private String childrenUrl = "children";
    private String childUrl = "children/{id}";
    private String addressUrl = "address/{id}";

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
    public Availability GetAvailabilityByPerson(int personid) {
        Person temp = GetPerson(personid);
        PerformQuery(baseUrl + availabilityUrl.replace("{id}", Integer.toString(personid)), null);
        AvailabilityResponse res = new AvailabilityResponse();
        res.ParseString(response.toString());
        return res.availability;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public Address GetAddressByPerson(int personid){
        Person temp = GetPerson(personid);
        PerformQuery(baseUrl + addressUrl.replace("{id}", Integer.toString(personid)), null);
        Address addr = new Gson().fromJson(response.toString(), Address.class);
        response = null;
        return addr;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public Person GetChildByParent(int parentid){
        PerformQuery(baseUrl + childUrl.replace("{id}", Integer.toString(parentid)), null);
        Person addr = new Gson().fromJson(response.toString(), Person.class);
        response = null;
        return addr;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public Event GetEventByID(int eventid){
        PerformQuery(baseUrl + eventUrl.replace("{id}", Integer.toString(eventid)), null);
        Event event = new Gson().fromJson(response.toString(), Event.class);
        response = null;
        return event;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public List<Event> GetEventsByParent(int parentid){
        //not sure yet..
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////
    Object response = null;
    ///////////////////////////////////////////////////////////////////////////////
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
