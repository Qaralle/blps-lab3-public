package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Place;

public interface PlaceService {
    boolean validate(Place place);
    Place create(String description);
}
