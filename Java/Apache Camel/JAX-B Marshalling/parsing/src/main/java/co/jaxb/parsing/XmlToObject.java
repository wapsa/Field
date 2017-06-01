package co.jaxb.parsing;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToObject {

	public static void main(String[] args) {
		try {

			File file = new File("xml/hello.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Customers custs = (Customers) jaxbUnmarshaller.unmarshal(file);
			List<Customer> custLst = custs.getCustomers();

			for (Customer cust : custLst) {

				System.out.println(cust.getName());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
