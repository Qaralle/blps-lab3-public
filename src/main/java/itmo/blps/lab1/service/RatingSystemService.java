package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Speaker;

public interface RatingSystemService {
    void calculateAndSetParticipationRate(Speaker speaker);
}
