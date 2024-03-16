package domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Passenger extends User {
	
	public Passenger(String name, int telephone, String email,  String password,boolean isDriver) {
		super(name,telephone, email, password, isDriver);
	}
	
}
