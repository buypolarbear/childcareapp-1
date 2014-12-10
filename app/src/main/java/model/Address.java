package model;

/**
 * Created by Braden on 11/29/2014.
 */
public class Address {

    public String city, state, zip, addressStr;

    public boolean Same(Address addr) {
        if(this.city.equals(addr.city) && this.state.equals(addr.state) && this.zip.equals(addr.zip) && this.addressStr.equals(addr.addressStr))
            return true;

        return false;
    }

}
