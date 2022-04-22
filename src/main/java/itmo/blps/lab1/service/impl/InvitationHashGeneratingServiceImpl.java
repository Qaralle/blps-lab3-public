package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.service.InvitationHashGeneratingService;
import org.springframework.stereotype.Service;

@Service
public class InvitationHashGeneratingServiceImpl implements InvitationHashGeneratingService {

    @Override
    public Long generateHash(Long speakerId, Long conferenceId) {
        return (long) (speakerId.toString() + conferenceId.toString()).hashCode();
    }
}
