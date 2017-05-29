package co.camel.ex;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

public class NoRoute extends RouteBuilder {

	public void configure() {
		Endpoint routeNO = endpoint("activemq:queue:routeNO");
		
		from(routeNO).bean(NoBean.class, "printB");

	}
}
