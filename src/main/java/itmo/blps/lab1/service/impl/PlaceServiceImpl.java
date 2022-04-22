package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.model.Place;
import itmo.blps.lab1.repository.PlaceRepository;
import itmo.blps.lab1.service.PlaceService;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {
    public final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public boolean validate(Place place) {
        if(place.getDescription().trim().length() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Place create(String description) {
        Place place = placeRepository.findPlaceByDescription(description);
        if (place != null) {
            return place;
        }
        return new Place(description);
    }
}
