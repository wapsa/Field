package co.camel.ex;

import org.apache.camel.Endpoint;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

public class RouteNew extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// you can define the endpoints and predicates here
		// it is more common to inline the endpoints and predicates in the route
		// as shown in the CreateOrderRoute

		Endpoint newOrder = endpoint("activemq:topic:newOrder");

		DataFormat jaxbDataFormat = new JaxbDataFormat("co.camel.ex");
		Predicate is = xpath("persons/person/name = 'name0'");
		from(newOrder).unmarshal(jaxbDataFormat).bean(Message.class, "echo");

		from(newOrder)
		.choice()
		.when(is).unmarshal(jaxbDataFormat).to("activemq:queue:routeYes")
				.bean(Message.class, "echo")
		.otherwise().unmarshal(jaxbDataFormat)
				.to("activemq:queue:routeNO").bean(Message.class, "echo");

	}
}
