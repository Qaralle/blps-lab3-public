package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Place;
import itmo.blps.lab1.model.Profile;
import itmo.blps.lab1.model.Speaker;
import itmo.blps.lab1.util.dataVue.TimePair;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ConferenceService {
    Conference createConference(String name, String description, Timestamp start, Timestamp finish, List<Profile> profiles, Place place) throws IllegalArgumentException;
    Conference addSpeakers(Conference conference, List<Speaker> speakers);
    boolean validateD(Timestamp start, Timestamp finish);
    Optional<Conference> findById(Long id);
    List<Conference> getUpcomingConferences();
    List<TimePair> getReservedTime();
    String conferenceToString(Conference conference);
    List<Conference> getAcceptedConferencesBySpeaker(Speaker speaker);
}
