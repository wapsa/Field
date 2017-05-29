package co.camel.listener;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


public class ConsumerMessageListener implements MessageListener {
	private String consumerName;

	public ConsumerMessageListener(String consumerName) {
		this.consumerName = consumerName;
	}
	
	public void onMessage(Message message) {
			
		if(message instanceof ObjectMessage)
		{
			try {
				User om = (User) ((ObjectMessage) message).getObject();
				System.out.println(consumerName + " received "
						+ (om.getName()));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
			
		
		
	}

}