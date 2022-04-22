package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Speaker;

import javax.mail.MessagingException;

public interface InvitationConfirmationService {
    void acceptInvitation(Long hash) throws MessagingException;
    void sendAdditionalInformation(Speaker speaker, Conference conference) throws MessagingException;

    boolean isInvitationAccepted(Long hash);
}
