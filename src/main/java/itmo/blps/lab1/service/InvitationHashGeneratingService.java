package itmo.blps.lab1.service;

public interface InvitationHashGeneratingService {

    Long generateHash(Long speakerId, Long conferenceId);
}
