package co.camel.filter;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.impl.DefaultCamelContext;

public class Listener {

	public static void main(String[] args) {
		try {
			CamelContext camelContext = new DefaultCamelContext();

			ActiveMQComponent comp = new ActiveMQComponent();
			comp.setBrokerURL("tcp://localhost:61616");
			comp.setUserName("admin");
			comp.setPassword("admin");
			camelContext.addComponent("activemq", comp);

			camelContext.addRoutes(new RouteBuilder() {
				public void configure() {

					// xmlns
					Namespaces ns = new Namespaces("ccc",
							"http://www.w3.org/2005/Atom");

					from("activemq:topic:topicTEST").filter(ns.xpath("//ccc:feed/ccc:author/ccc:name/text() = 'Ram Satish'"))
							.to("stream:out");

				}
			});
			camelContext.start();
		} catch (CamelExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
