package co.camel.filter;

import java.io.File;
import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

	private String theBrokerUrl;
	private String theUserName;
	private String thePassword;
	private String theTopicName;
	private String theConnectionFactory;
	private MessageProducer theProducer;
	private String theContextFactory;
	private Context theContext;
	private Connection theConnection;
	private Session theSession;

	// Logger for the class
	private static final Logger theLogger = LoggerFactory.getLogger(Producer.class);

	public void sendDataIntoActiveMqTopic() {
		loadPropertiesFromFile();

		// Create environment for initial context
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, theContextFactory);
		environment.put(Context.PROVIDER_URL, theBrokerUrl);
		environment.put(Context.SECURITY_PRINCIPAL, theUserName);
		environment.put(Context.SECURITY_CREDENTIALS, thePassword);

		try {
			// Create context with an environment
			theContext = new InitialContext(environment);

			// Get ConnectionFactory
			ConnectionFactory connectionFactory = (ConnectionFactory) theContext.lookup(theConnectionFactory);

			// Create Connection using the userName and password
			theConnection = connectionFactory.createConnection(theUserName, thePassword);

			// Connection start
			theConnection.start();

			// Create session
			theSession = theConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Get Destination
			Destination destination = theSession.createTopic(theTopicName);

			// Create producer to send the message
			theProducer = theSession.createProducer(destination);

			
			// Create Object message to send the data
			ObjectMessage message = theSession.createObjectMessage(new File("src/main/resources/order1.xml"));
				 
			theProducer.send(message);
			
			
		} catch (NamingException e) {
			  System.out.println("Error creating the producer: " + e);
		         System.exit(1);
		} catch (JMSException e) {
			  System.out.println("Error creating the producer: " + e);
		         System.exit(1);
		}

	}

	public void loadPropertiesFromFile() {

		
		//Properties theProp = new Properties();
		try {
			//theProp.load(new FileInputStream("conf/activemqcredentials.properties"));
			theUserName ="admin";

			thePassword = "admin";
			theContextFactory = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
			theBrokerUrl = "tcp://localhost:61616";
			theConnectionFactory = "ConnectionFactory";
			theTopicName = "topicTEST";
		} catch (NullPointerException e) {
			theLogger.error("Error. The properties file should be located at "  + ": ", e);
			System.exit(1);
		} catch (ClassCastException e) {
			theLogger.error("Error. The properties file contains non-string values: ", e);
			System.exit(1);
		} 

	}

	public static void main(String[] args) {
		Producer m = new Producer();
		m.sendDataIntoActiveMqTopic();
	}
}
