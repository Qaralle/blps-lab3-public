package itmo.blps.lab1.service;

public interface MailService {
    void sendMail(String email, String text);
    void sendMail(String email, String text, boolean isHTML);
}
