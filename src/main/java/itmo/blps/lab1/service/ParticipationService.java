package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Speaker;

import javax.mail.MessagingException;

public interface ParticipationService {
    void add(Speaker speaker, Long conferenceId) throws MessagingException;
}
