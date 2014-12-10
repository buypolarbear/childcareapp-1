package model;

import java.util.List;

/**
 * Created by Braden on 11/29/2014.
 */
public class Person {


    public int idperson = -1;
    public String firstname;
    public String lastname;
    public String phonenumber;

    public Address address;

    public Availability availability;

    public List<Child> children;

    public List<Event> scheduledEvents;

}
