package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class User {
	@XmlID
	@Id 
	private String email;
	private String name;
	private int telephone;
	private float cash;
	private String password;
	private boolean isDriver;
	
	public User (String name, int telephone, String email, String password, boolean isDriver) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.telephone = telephone;
		this.isDriver = isDriver;
		this.cash = 0;
	}
	
	public float getCash() {
		return cash;
	}
	
	public void setCash(float cash) {
		this.cash = cash;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	
	public int getTelephone() {
		return telephone;
	}
	
	public boolean getIsDriver() {
		return isDriver;
	}
}
