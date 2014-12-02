package model;

/**
 * Created by Braden on 12/1/2014.
 */
public class Availability {

    //times are stored in a 24 hours format, 0 - 24 hours
    public int personid = -1;
    public int[] startTimes = new int[7];
    public int[] endTimes = new int[7];
}
