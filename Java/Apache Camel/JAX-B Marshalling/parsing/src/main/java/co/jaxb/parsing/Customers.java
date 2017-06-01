package co.jaxb.parsing;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Customers")
public class Customers {

	@XmlElement(name = "customer")
	List<Customer> customer;

	public List<Customer> getCustomers() {
		return customer;
	}

	public void setCustomers(List<Customer> customers) {
		this.customer = customers;
	}

}
