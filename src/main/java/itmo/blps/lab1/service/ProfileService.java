package itmo.blps.lab1.service;

import itmo.blps.lab1.dto.ProfileDTO;
import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Option;
import itmo.blps.lab1.model.Profile;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProfileService {
    boolean validate(Profile profile);
    Profile create(String name, Double price, List<Option> options);
    List<Profile> findAllByConference(Conference conference);
    void save(Profile profile);
    List<Profile> crateList(List<ProfileDTO> raws, Map<String, Option> options);
}
