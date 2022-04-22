package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Participation;
import itmo.blps.lab1.model.Speaker;

import java.util.List;

public interface SpeakerService {
    Speaker createAndSaveSpeaker(String name, String email) throws IllegalArgumentException;
    List<Speaker> getAllSpeakers();
    void submitSpeakersToConf(List<Speaker> speakers, Long conferenceId);
    Participation submitSpeakerToConf(Speaker speaker, Long conferenceId);
    List<Speaker> getAllSpeakersById(List<Long> idList);
    List<Speaker> getAllSpeakerByConference(Conference conference);
}
