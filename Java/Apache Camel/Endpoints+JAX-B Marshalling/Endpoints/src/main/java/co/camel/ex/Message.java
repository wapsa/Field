package co.camel.ex;

public class Message {
	
	public void echo(Persons p) {
		System.out.println("Message:" + p.getPersons().get(1).getName());
    }
}
