package emailer;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import role.IAdmin;
import role.User;

public class Emailer {
	
	public void sendToUsers(ArrayList<User> users,String message, String subj)
	{
		for (User user : users) {
			if (!(user instanceof IAdmin))
			sendMail(message, user.getEmail(), subj);
		}
	}
	
	public void sendMail(String strmessage,String to,String Subj)
	{
		
		 	Properties properties = new Properties();
	        properties.put("mail.smtp.host"               , "smtp.yandex.ru");
	        properties.put("mail.smtp.port"               , "465"  );
	        properties.put("mail.smtp.auth"               , "true"     );
	        properties.put("mail.smtp.ssl.enable"         , "true"     );
	        properties.put("mail.smtp.socketFactory.class", 
	                                   "javax.net.ssl.SSLSocketFactory");
	        try {
	            Authenticator auth = new EmailAuthenticator("cvobodn@yandex.ru",
	                                                        "JasdoFy123");
	            Session session = Session.getDefaultInstance(properties,auth);
	            session.setDebug(false);

	            
	            InternetAddress email_from = new InternetAddress("cvobodn@yandex.ru");
	            InternetAddress email_to   = new InternetAddress(to);

	            MimeMessage message = new MimeMessage(session);
	            
	            message = new MimeMessage(session); 
	            message.setFrom(email_from);   
	            message.setRecipient(Message.RecipientType.TO, email_to);
	            message.setSubject(Subj);
	            message.setText(strmessage);
	            
	            Transport.send(message);

	        } catch (AddressException e) {
	            System.err.println(e.getMessage());
	        } catch (MessagingException e) {
	            System.err.println(e.getMessage());
	        }
        
        
	}
	public class EmailAuthenticator extends Authenticator
	{
	    private String login   ;
	    private String password;
	    public EmailAuthenticator (final String login, final String password)
	    {
	        this.login    = login;
	        this.password = password;
	    }
	    public javax.mail.PasswordAuthentication getPasswordAuthentication()
	    {
	    	javax.mail.PasswordAuthentication PA = new javax.mail.PasswordAuthentication(login, password);
	        return PA;
	    }
	}
}

