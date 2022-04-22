package itmo.blps.lab1.service.impl;

import cum.company.MailMessage;
import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Speaker;
import itmo.blps.lab1.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final SpeakerService speakerService;
    private final ConferenceService conferenceService;
    private final MailService mailService;

    public NotificationServiceImpl(SpeakerService speakerService, ConferenceService conferenceService, MailService mailService) {
        this.speakerService = speakerService;
        this.conferenceService = conferenceService;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(cron="0 07 19 * * *", zone="Europe/Moscow")
    public void eveningNotification() {
        List<Conference> upcomingConferences = conferenceService.getUpcomingConferences();
        Set<Speaker> mustBeNotified = new HashSet<>();
        upcomingConferences.forEach(c->mustBeNotified.addAll(speakerService.getAllSpeakerByConference(c)));

        mustBeNotified.forEach( s -> prepareListOfConference(s,conferenceService.getAcceptedConferencesBySpeaker(s)));
    }


    private void prepareListOfConference(Speaker speaker, List<Conference> conferences)  {
        StringBuilder msgText = new StringBuilder();

        msgText.append("У вас есть предстоящие конференции на завтра:\n");
        conferences.forEach(c->msgText.append(conferenceService.conferenceToString(c)));
        msgText.append("Fuck you, "+speaker.getName());


        mailService.sendMail(speaker.getEmail(),msgText.toString());
    }
}
