package co.camel.ex;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class YesRoute extends RouteBuilder {
	public void configure() {
		// errorHandler(deadLetterChannel("mock:error"));
		Endpoint routeYes = endpoint("activemq:queue:routeYes");
		
		from(routeYes).bean(YesBean.class, "printA");

	}
}


