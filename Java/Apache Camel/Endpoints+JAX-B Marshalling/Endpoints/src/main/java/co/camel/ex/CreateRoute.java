package co.camel.ex;

import org.apache.camel.builder.RouteBuilder;

public class CreateRoute extends RouteBuilder{

	public void configure() {
		from("file:src/main/resources?noop=true")
				.to("activemq:topic:newOrder");

	}
}
