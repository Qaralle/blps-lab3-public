package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Invitation;
import itmo.blps.lab1.model.Participation;
import itmo.blps.lab1.model.Speaker;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

public interface InviteService {


    void inviteSpeaker(Participation participation) throws MessagingException;
    boolean inviteList(List<Speaker> speakers, Long conferenceId) throws MessagingException;
    Invitation generateInvite(Participation participation);
    boolean isInvited(Long speakerId, Long conferenceId);

    boolean isInvited(Long hash);
}
