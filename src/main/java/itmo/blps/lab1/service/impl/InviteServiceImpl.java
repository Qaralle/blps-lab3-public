package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.model.Invitation;
import itmo.blps.lab1.model.Participation;
import itmo.blps.lab1.model.Speaker;
import itmo.blps.lab1.repository.InvitationRepository;
import itmo.blps.lab1.service.InvitationHashGeneratingService;
import itmo.blps.lab1.service.InviteService;
import itmo.blps.lab1.service.MailService;
import itmo.blps.lab1.service.SpeakerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

@Service
public class InviteServiceImpl implements InviteService {

    private final InvitationRepository invitationRepository;
    private final InvitationHashGeneratingService invitationHashGeneratingService;
    private final SpeakerService speakerService;
    private final MailService mailService;

    public InviteServiceImpl(InvitationRepository invitationRepository, InvitationHashGeneratingService invitationHashGeneratingService, SpeakerService speakerService, MailService mailService) {
        this.invitationRepository = invitationRepository;
        this.invitationHashGeneratingService = invitationHashGeneratingService;
        this.speakerService = speakerService;
        this.mailService = mailService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {MessagingException.class, SQLException.class})
    public void inviteSpeaker(Participation participation){
        Speaker speaker = participation.getSpeaker();
        Invitation invitation = generateInvite(participation);
        String text = "<p><span>Радистка Кэт вела Штирлица через темный двор.</span></p>\n" +
                "<p><span>\"Не споткнись об пса,\" - сказала Кэт.</span></p>\n" +
                "<p><span>Штирлиц споткнулся, раздался кошачий визг.</span></p>\n" +
                "<p><span>\"<a href = 'http://localhost:8083/api/app/confirmInvitation/" + invitation.getHash() + "'>Об манула</a>\", - подумал Штирлиц</span></p>";
        mailService.sendMail(speaker.getEmail(), text, true);

        invitationRepository.save(invitation);
    }



    @Override
    public boolean inviteList(List<Speaker> speakers, Long conferenceId)  {
//        transactionTemplate.execute(new TransactionCallbackWithoutResult()  {
//            protected void doInTransactionWithoutResult(TransactionStatus status){
//                int i = 0;
//                for(Speaker s : speakers) {
//                    if(!isInvited(s.getId(), conferenceId)) {
//                        inviteSpeaker(s, conferenceId);
//                    }
//                    i++;
//                }
//                speakers.forEach(s -> {
//                            if(!isInvited(s.getId(), conferenceId)) {
//                                inviteSpeaker(s, conferenceId, i);
//                            }
//                        }
//                );
//            }
//        });

        return true;
    }



    @Override
    @Transactional
    public Invitation generateInvite(Participation participation) {
        Long speakerId = participation.getSpeaker().getId();
        Long conferenceId = participation.getConference().getId();
        Invitation invitation = invitationRepository.getInvitationByParticipation_Conference_IdAndParticipation_Speaker_Id(conferenceId, speakerId);
        if(invitation != null) {
            if(invitation.getParticipation().getIsAccepted()) {
                throw new RuntimeException("Speaker has already accepted invitation");
            }else {
                return invitation;
            }
        }else {
            return new Invitation(invitationHashGeneratingService.generateHash(speakerId, conferenceId), participation);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isInvited(Long speakerId, Long conferenceId) {
        Invitation invitation = invitationRepository.getInvitationByParticipation_Conference_IdAndParticipation_Speaker_Id(conferenceId, speakerId);
        if(invitation != null) {
            return true;
        }else {
            return false;
        }
    }


    @Override
    @Transactional
    public boolean isInvited(Long hash) {
        Invitation invitation = invitationRepository.getFirstByHash(hash);
        if(invitation != null) {
            return true;
        }
        return false;
    }
}
