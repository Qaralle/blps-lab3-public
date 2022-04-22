package itmo.blps.lab1.service.impl;

import cum.company.MailMessage;
import itmo.blps.lab1.service.MailService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements MailService {
    private final KafkaTemplate<String, MailMessage> kafkaTemplate;

    public MailServiceImpl(KafkaTemplate<String, MailMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMail(String email, String text){
        sendMail(email, text, false);
    }

    @Override
    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendMail(String email, String text, boolean isHTML){
        MailMessage msg = new MailMessage();

        msg.setEmail(email);
        msg.setText(text);
        msg.setHTML(isHTML);
        kafkaTemplate.send("mail-topic", msg);
    }
}
