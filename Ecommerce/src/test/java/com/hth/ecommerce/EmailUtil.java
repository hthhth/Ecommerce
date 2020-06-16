package com.hth.ecommerce;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	public static void sendFileToEmail(String fromEmail, String password, String toEmail, String filePath) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Send attachment of orders.csv");
            
//            String userHome = System.getProperty("user.home");
//            String filePath = userHome + "/Downloads/orders.csv";
            DataSource source = new FileDataSource(filePath);
            
            BodyPart bodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            
            bodyPart.setText("Please find the attached csv");
            multipart.addBodyPart(bodyPart);
            
            bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName(source.getName());
            multipart.addBodyPart(bodyPart);
            
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Sent email successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
