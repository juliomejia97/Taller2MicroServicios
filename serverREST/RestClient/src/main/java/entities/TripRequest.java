package entities;

public class TripRequest {
	private String name;
    private String departure;
    private String arrival;
    private String departure_date;
    private String arrival_date;

    public TripRequest() {
    }
    
    public TripRequest(String name, String departure, String arrival, String departure_date, String arrival_date) {
		super();
		this.name = name;
		this.departure = departure;
		this.arrival = arrival;
		this.departure_date = departure_date;
		this.arrival_date = arrival_date;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

}
