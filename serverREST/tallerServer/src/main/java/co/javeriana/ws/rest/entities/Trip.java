package co.javeriana.ws.rest.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class Trip implements Serializable {
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

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    @XmlElement
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    @XmlElement
    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }
    @XmlElement
    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }
}
