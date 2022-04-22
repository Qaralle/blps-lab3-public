package itmo.blps.lab1.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import itmo.blps.lab1.dto.ConferenceDTO;
import itmo.blps.lab1.model.Conference;
import itmo.blps.lab1.model.Option;
import itmo.blps.lab1.model.Profile;
import itmo.blps.lab1.repository.xml.UserRepository;
import itmo.blps.lab1.service.ConferenceService;
import itmo.blps.lab1.service.OptionService;
import itmo.blps.lab1.service.PlaceService;
import itmo.blps.lab1.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/app/")
@Tag(name="Conference manipulation")
public class ConferencesRestController {
    private final ProfileService profileService;
    private final ConferenceService conferenceService;
    private final PlaceService placeService;
    private final OptionService optionService;
    private final UserRepository userRepository;


    public ConferencesRestController(ProfileService profileService, ConferenceService conferenceService, PlaceService placeService, OptionService optionService, UserRepository userRepository) {
        this.profileService = profileService;
        this.conferenceService = conferenceService;
        this.placeService = placeService ;
        this.optionService = optionService;
        this.userRepository = userRepository;
    }

    @PostMapping("create_conference")
    @Operation(summary = "Create conference",
            description = "Create conference")
    @Parameter(in = ParameterIn.QUERY, name = "много короче", required = true,
            schema = @Schema(type = "string"),
            style = ParameterStyle.FORM, example = "valera")
    public ResponseEntity create(@RequestBody ConferenceDTO req) {
        Conference conference;

        Map<String, Option> options = optionService.createList(req.getAllOptions());
        List<Profile> profiles =profileService.crateList(req.getProfiles(),options);

        try {
             conference = conferenceService.createConference(req.getName(),req.getDescription(),
                    req.getStart(),req.getFinish(),profiles,placeService.create(req.getPlace_description()));
        }catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok(profileService.findAllByConference(conference));
    }

    @GetMapping("get_reserved_time")
    @Operation(summary = "Get reserved times",
            description = "Get list of reserved times")
    public ResponseEntity getReservedTime() {
        return ResponseEntity.ok(conferenceService.getReservedTime());
    }


    @GetMapping("test")
    @Operation(summary = "Get reserved times",
            description = "Get list of reserved times")
    public ResponseEntity test() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
