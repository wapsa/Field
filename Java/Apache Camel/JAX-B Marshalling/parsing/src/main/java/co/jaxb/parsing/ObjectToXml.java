package co.jaxb.parsing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ObjectToXml {

	public static void main(String[] args) {

		try {
			Customer cust = new Customer();
			cust.setName("Sau");
			cust.setAge(25);
			cust.setId(01);
			Customers custs = new Customers();
			List<Customer> custLst = new ArrayList<Customer>();
			custLst.add(cust);
			custs.setCustomers(custLst);

			File file = new File("xml/hello.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(custs, file);
			jaxbMarshaller.marshal(custs, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
