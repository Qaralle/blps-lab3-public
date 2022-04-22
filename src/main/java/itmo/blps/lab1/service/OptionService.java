package itmo.blps.lab1.service;

import itmo.blps.lab1.model.Option;

import java.util.List;
import java.util.Map;

public interface OptionService {
    Option create(String description);
    Map<String,Option> createList(List<String> raws);
}
