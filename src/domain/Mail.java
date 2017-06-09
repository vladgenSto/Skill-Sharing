package domain;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	private Properties props;
	private Session sesion;
	private MimeMessage message;
	private Transport t;

	public Mail(){
		props=new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.user", "ei102716ps@gmail.com");
		props.setProperty("mail.smtp.user", "true");
		sesion = Session.getInstance(props, new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("ei102716ps@gmail.com", "perisstoyanov");
		    }
		});
		sesion.setDebug(true);
	}
	public void enviarMensaje(String asunto, String mensaje, List<String>destinatarios) throws AddressException, MessagingException{
		message=this.crearMensaje(asunto, mensaje, destinatarios);
		this.realizarEnvio(message);
	}
	private MimeMessage crearMensaje(String asunto, String mensaje, List<String>destinatarios) throws AddressException, MessagingException{
		MimeMessage message=new MimeMessage(sesion);
		message.setFrom(new InternetAddress("ei102716ps@gmail.com"));
		for(String destinatario: destinatarios){
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		}
		message.setSubject(asunto);
		message.setText(mensaje);
		return message;
	}
	
	private void realizarEnvio(MimeMessage message) throws MessagingException{
		t=sesion.getTransport("smtp");
		t.connect("ei102716ps@gmail.com","perisstoyanov");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
	}
}
