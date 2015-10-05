package com.codejock.utilities.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;

public class EMailTools {
	/*
	String host = "smtp-server.nyc.rr.com";
		String from = "bwynne17@earthlink.net";
		String to = "bwynne@nyc.rr.com";
		String filename = "resources/img/TS.jpg";
	*/
	protected String host;
	protected String from;
	protected String to;
	protected Multipart multipart;
	protected Properties props;
	protected Session session;
	protected Message message;
	{
		multipart = new MimeMultipart();
	}

	public EMailTools( String host_name, String sender, String recipient )
		throws javax.mail.internet.AddressException, javax.mail.MessagingException
	{
		host = host_name;
		sender = from;
		to = recipient;
		props = System.getProperties();
		props.put("mail.smtp.host", host);
		session = Session.getInstance(props, null);
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		addRecipient ( to, Message.RecipientType.TO );

	}

	protected void setSubject( String subject ) throws javax.mail.MessagingException
	{
		message.setSubject( subject );
	}

	protected void setTextBody( String text ) throws javax.mail.MessagingException
	{
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText( text );

		// Create a Multipart
		Multipart multipart = new MimeMultipart();

		// Add part one
		multipart.addBodyPart(messageBodyPart);
	}

	protected void setHTMLBody( String html )  throws javax.mail.MessagingException
	{
		// Create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText( html, "text/html" );

		// Create a Multipart
		Multipart multipart = new MimeMultipart();

		// Add part one
		multipart.addBodyPart(messageBodyPart);
	}

	protected void addRecipient( String recipient, Message.RecipientType rt )
		throws javax.mail.internet.AddressException, javax.mail.MessagingException
	{
		InternetAddress in_add = new InternetAddress( to );
		message.addRecipient( rt, in_add );
	}

	protected void addAttachment( String path ) throws javax.mail.MessagingException
	{
		// Create second body part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Get the attachment
		DataSource source = new FileDataSource( path );

		// Set the data handler to the attachment
	 	messageBodyPart.setDataHandler(new DataHandler(source));

		// Set the filename
	 	messageBodyPart.setFileName( path );

		// Add part two
	 	multipart.addBodyPart(messageBodyPart);
	 	// Put parts in message
		message.setContent(multipart);
	}

	protected void Transmit() throws javax.mail.MessagingException
	{
		// Send the message
		Transport.send(message);
	}
}