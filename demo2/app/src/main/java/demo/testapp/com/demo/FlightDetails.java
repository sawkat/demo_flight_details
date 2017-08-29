package demo.testapp.com.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sawkat.ali on 8/29/2017.
 */

public class FlightDetails {
    private String flight_number;
    private ArrayList<String> links = new ArrayList<>();
    private String launch_year;

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }



    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }


    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }
}
