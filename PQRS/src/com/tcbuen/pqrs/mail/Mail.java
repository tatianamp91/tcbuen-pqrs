package com.tcbuen.pqrs.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Mail implements IMail{
    private final Properties properties = new Properties();
    private Session session;

	private void init() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender", "tcbuenpqrs@gmail.com");
        properties.put("mail.smtp.password", "tcbuen123");
        properties.put("mail.smtp.user", "tcbuenpqrs@gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session = Session.getDefaultInstance(properties);
    }
    @Override
    public void send(String destino,String asunto, String mensaje) {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport t = session.getTransport("smtp");            
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            return;
        }
    }
}