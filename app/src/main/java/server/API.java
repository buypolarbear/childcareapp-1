package server;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.Address;
import model.Availability;
import model.Child;
import model.Person;

/**
 * Created by Braden on 11/29/2014.
 */
public class API {

    private String baseUrl = "http://childcareappservice.azurewebsites.net/api/";
   // private String baseUrl = "http://10.0.0.5:5142/api/"; //THIS STRING IS FOR LOCAL TESTING
    private String peopleUrl = "people";
    private String personUrl = "people/{id}";
    private String availabilityUrl = "availabilities";
    private String eventsUrl = "event";
    private String eventUrl = "event/{id}";
    private String childrenUrl = "children";
    private String childUrl = "children/{id}";
    private String addressUrl = "addresses";

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
    /*public List<Integer> AddChildren(List<Child> children) {
        List<Integer> childid = new ArrayList<Integer>();

        if(children == null) return childid;

        for(Child child : children) {
            PerformQuery(baseUrl + childrenUrl, APIHelpers.ComposeChild(child)); //should block until finished
        }

        return childid;
    }*/
    ///////////////////////////////////////////////////////////////////////////////
   /* public int AddAvailability(Availability availability) {
        PerformQuery(baseUrl + availabilityUrl, APIHelpers.ComposeAvailability(availability)); //should block until finished
        int i = APIHelpers.ParseNewResponseID(response);
        response = null;
        return i;
    }*/
    ///////////////////////////////////////////////////////////////////////////////
    public void AddPerson(Person person, Address addr, List<Child> children, Availability availability) {

        /*person.addressid = AddAddress(addr);
        AddChildren(children);
        AddAvailability(availability);*/


        person.address = addr;
        person.availability = availability;
        person.children = children;
        person.scheduledEvents = null;

        try {
            PerformQuery(baseUrl + peopleUrl, new JSONObject(new Gson().toJson(person))); //should block until finished
        } catch(Exception e) { e.printStackTrace(); }

        //int id = APIHelpers.ParseNewResponseID(response);
        response = null;
    }
    ///////////////////////////////////////////////////////////////////////////////
    public Address[] GetAddresses() {
        PerformQuery(baseUrl + addressUrl, null);
        Address[] arr = new Gson().fromJson(response.toString(), Address[].class);
        response = null;
        return arr;
    }
    ///////////////////////////////////////////////////////////////////////////////
    private int CompareAddresses(Address addr) {
        Address[] arr = GetAddresses();
        for(Address a : arr) {
            if (a.Same(addr))
                return a.idaddress;
        }
        return -1;
    }
    ///////////////////////////////////////////////////////////////////////////////
    /*public int AddAddress(Address addr) {
        //check to make sure it's not already in there
        int id = CompareAddresses(addr);
        if(id != -1) return id;


        PerformQuery(baseUrl + addressUrl, APIHelpers.ComposeAddress(addr));

        //we need to return the new addressStr id
        id = CompareAddresses(addr);
        response = null;
        return id;
    }*/
    ///////////////////////////////////////////////////////////////////////////////
    /*public Address GetAddressByPerson(int personid){
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
    }*/
    ///////////////////////////////////////////////////////////////////////////////
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

//##################################################################################################################////
class APIHelpers {

    ///////////////////////////////////////////////////////////////////////////////
    public static int ParseNewResponseID(Object response) {
        String str = response.toString();
        String sub = str.substring(str.indexOf("id"));
        return Integer.parseInt(sub);
    }
    ///////////////////////////////////////////////////////////////////////////////
    private static String ComposeTimes(int[] times) {
        String ret = "";
        for(int i : times) {
            ret += i + ":";
        }
        return ret;
    }
    ///////////////////////////////////////////////////////////////////////////////
    private static int[] DecomposeTimes(String times) {
        int[] ret = new int[7];

        String[] vals = times.split(":");
        int index = 0;
        for(String str : vals) {
            int val = Integer.parseInt(str);
            ret[index++] = val;
        }
        return ret;
    }
    ///////////////////////////////////////////////////////////////////////////////
}
