package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
	private int id;
    private String name;
    private String departure;
    private String arrival;
    private Date departure_date;
    private Date arrival_date;

    public Trip(){}
    public Trip(int id, String name, String departure, String arrival, Date departure_date, Date arrival_date) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }
    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }
	@Override
	public String toString() {
		String departureString = new SimpleDateFormat("yyyy-MM-dd").format(departure_date);
		String arrivalString = new SimpleDateFormat("yyyy-MM-dd").format(arrival_date);
		return "Trip [id=" + id + ", name=" + name + ", departure=" + departure + ", arrival=" + arrival
				+ ", departure_date=" + departureString + ", arrival_date=" + arrivalString + "]";
	}
    
}
