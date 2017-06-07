package com.maven.project.consumer.service.mail;

// Imports--------------------------------------------------
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	// Methods--------------------------------------------------
	public void sendFromGMail(String[] recipient, String activationLink) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		String username = "wapsatest@gmail.com";
		String password = "wapsatest123";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {

			message.setFrom(new InternetAddress(username));
			InternetAddress[] toAddress = new InternetAddress[recipient.length];

			// To get the array of addresses
			for (int i = 0; i < recipient.length; i++) {
				toAddress[i] = new InternetAddress(recipient[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject("Account Activation Email");
			message.setContent(
					"<h1>ACTIVATION EMAIL</h1>" + "<br><br>" + "Click on the link below to activate your account." + "<br>"
							+ "LINK: " + activationLink + " <br><br>"
							+ "If the link doesnt work copy and paste the url in your browser and press enter.",
					"text/html");
			// message.setText(body, "utf-8", "html");
			Transport transport = session.getTransport("smtp");
			System.out.println("TRYING TO CONNECT NOW");
			transport.connect(host, username, password);
			System.out.println("CONNECTED");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("SUCCESS");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}