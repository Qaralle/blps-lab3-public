package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.dto.ProfileDTO;
import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Option;
import itmo.blps.lab1.model.Profile;
import itmo.blps.lab1.repository.ProfileRepository;
import itmo.blps.lab1.service.ProfileService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repository;

    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validate(Profile profile) {

        if(profile.getName().trim().length() == 0) {
            return false;
        }else if(profile.getPrice() < 0) {
            return false;
        }else if(profile.getOptions().size() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public Profile create(String name, Double price, List<Option> options) {
        return new Profile(name,price,options);
    }

    @Override
    public List<Profile> findAllByConference(Conference conference) {
        return repository.findAllByConference(conference);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Profile profile) {
        repository.save(profile);
    }

    @Override
    public List<Profile> crateList(List<ProfileDTO> raws,  Map<String, Option> options) {
        List<Profile> profiles = new ArrayList<>();

        raws.forEach(i-> {
            List<Option> concreteOptions = new ArrayList<>();
            for(Map.Entry<String, Boolean> entry: i.getOptions().entrySet()) {
                concreteOptions.add(options.get(entry.getKey()));
            }
            profiles.add(create(i.getName(),
                    i.getPrice(), concreteOptions));
        });

        return profiles;
    }


}
