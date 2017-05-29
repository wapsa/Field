package co.camel.listener;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -2871623221750554972L;
	
	String name;
	String city;

	
	public User(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
