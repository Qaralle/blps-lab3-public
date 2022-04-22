package itmo.blps.lab1.repository;

import itmo.blps.lab1.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findPlaceByDescription(String description);
}
