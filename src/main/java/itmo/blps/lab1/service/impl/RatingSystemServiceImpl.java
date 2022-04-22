package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.model.Speaker;
import itmo.blps.lab1.repository.SpeakerRepository;
import itmo.blps.lab1.service.RatingSystemService;
import org.springframework.stereotype.Service;

@Service
public class RatingSystemServiceImpl implements RatingSystemService {
    private SpeakerRepository speakerRepository;

    public RatingSystemServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public void calculateAndSetParticipationRate(Speaker speaker) {
        speaker.setRate(speaker.getRate() + 10);
        speakerRepository.save(speaker);
    }
}
