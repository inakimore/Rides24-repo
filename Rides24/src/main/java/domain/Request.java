package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Request {
	
	private static int cont = 1;
	
	@XmlID
	@Id
	private int requestNumber;
	private Ride ride;
	private Passenger passenger;
	
	public Request(Ride ride, Passenger passenger) {
		this.ride = ride;
		this.passenger = passenger;
		this.requestNumber = cont;
		cont++;
	}
	
	public Ride getRide() {
		return ride;
	}
	
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public int getRequestNumber() {
		return this.requestNumber;
	}
}
