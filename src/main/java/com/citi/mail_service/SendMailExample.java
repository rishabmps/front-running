package com.citi.mail_service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailExample {

	private String from;
	private String to;
	private String subject;
	private String messageBody;
	private String host;

	private Properties properties;

	private MimeMessage message;
	private BodyPart messageBodyPart;
	private Multipart multipart;

	private Authenticator authenticator;

	public SendMailExample() {
		from = "front.team17@gmail.com";
		// to = "nairsoumya85@gmail.com";
		to = "front.team17@gmail.com";
		subject = "ALERT!! ACTION REQUIRED!! Front Running Trades Detected";
		messageBody = "<html><body><h1>HAVE FAITH, AND SUPPORT MSD" + ":-)</h1></body></html>";
		// fileName = "quiz.txt";
		host = "smtp.gmail.com";

		authenticator = new SMTPAuthenticator();
		properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
	}

	private void sendMail(String from, String to, String subject, String messageBody) {
		try {
			Session session = Session.getInstance(properties, authenticator);
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(messageBody, "text/html");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			
			me.printStackTrace();
		}
	}

	public void performTask(String messageBody) {
		sendMail(from, to, subject, messageBody);
	}

	// public static void main(String[] args) {
	// new SendMailExample().performTask("<html><body><h1>Always SUPPORT MSD" +
	// ":-)</h1></body></html>");
	// }
}

/**
 * SimpleAuthenticator is used to do simple authentication when the SMTP server
 * requires it.
 */

class SMTPAuthenticator extends Authenticator {

	private static final String SMTP_AUTH_USER = "front.team17@gmail.com";
	private static final String SMTP_AUTH_PASSWORD = "frontrunning";

	public PasswordAuthentication getPasswordAuthentication() {
		String username = SMTP_AUTH_USER;
		String password = SMTP_AUTH_PASSWORD;

		return new PasswordAuthentication(username, password);
	}
}