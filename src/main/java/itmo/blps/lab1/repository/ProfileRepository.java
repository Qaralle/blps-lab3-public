package itmo.blps.lab1.repository;

import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findAllByConference(Conference conference);
}
