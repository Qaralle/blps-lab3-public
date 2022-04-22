package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.model.Option;
import itmo.blps.lab1.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OptionServiceImpl implements OptionService {
    @Override
    public Option create(String description) {
        return new Option(description, new ArrayList<>());
    }

    @Override
    public Map<String,Option> createList(List<String> raws) {
        Map<String, Option> options = new HashMap<>();

        raws.forEach(o->options.put(o, create(o)));
        return options;
    }
}
