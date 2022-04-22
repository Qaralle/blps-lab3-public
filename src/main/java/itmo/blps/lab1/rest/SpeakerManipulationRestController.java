package itmo.blps.lab1.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import itmo.blps.lab1.dto.SpeakerDTO;
import itmo.blps.lab1.dto.SpeakersDTO;
import itmo.blps.lab1.model.Speaker;
import itmo.blps.lab1.service.InvitationConfirmationService;
import itmo.blps.lab1.service.InviteService;
import itmo.blps.lab1.service.ParticipationService;
import itmo.blps.lab1.service.SpeakerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api/app/")
@Tag(name="Speaker manipulation")
public class SpeakerManipulationRestController {
    private final SpeakerService speakerService;
    private final InviteService inviteService;
    private final InvitationConfirmationService invitationConfirmationService;
    private final ParticipationService participationService;

    public SpeakerManipulationRestController(SpeakerService speakerService, InviteService inviteService, InvitationConfirmationService invitationConfirmationService, ParticipationService participationService) {
        this.speakerService = speakerService;
        this.inviteService = inviteService;
        this.invitationConfirmationService = invitationConfirmationService;
        this.participationService = participationService;
    }

    @GetMapping("get_speaker_list")
    @Operation(summary = "Get speakers",
            description = "Get list of all speakers")
    public ResponseEntity getSpeakerList(){
        return ResponseEntity.ok(speakerService.getAllSpeakers());
    }

    @PostMapping("create_speaker")
    @Operation(summary = "Create speaker",
            description = "Create speakers")
    @Parameter(in = ParameterIn.QUERY, name = "name", required = true,
            schema = @Schema(type = "string"),
            style = ParameterStyle.FORM, example = "valera")
    @Parameter(in = ParameterIn.QUERY, name = "email", required = true,
            schema = @Schema(type = "string"),
            style = ParameterStyle.FORM, example = "valerinemail@shtirlec.com")
    public ResponseEntity create(@RequestBody SpeakerDTO req){
        Speaker speaker;
        try {
            speaker = speakerService.createAndSaveSpeaker(req.getName(), req.getEmail());
        }catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok(speaker);
    }

    @PostMapping("submit_speakers")
    @Operation(summary = "Submit speakers",
            description = "Submit speakers on the conference")
    @Parameter(in = ParameterIn.QUERY, name = "conferenceId", required = true,
            schema = @Schema(type = "long"),
            style = ParameterStyle.FORM, example = "1337")
    @Parameter(in = ParameterIn.QUERY, name = "speakersIdList", required = true,
            schema = @Schema(type = "object"),
            style = ParameterStyle.FORM, example = "[1,4,8,8]")
    public ResponseEntity submitSpeakers(@RequestBody SpeakersDTO req) {
        List<Long> speakerIdList = req.speakerIdList;
        List<Speaker> speakerList = speakerService.getAllSpeakersById(speakerIdList);
        try {
            long conferenceId = req.conferenceId;

            for(Speaker s : speakerList) {
                if(!inviteService.isInvited(s.getId(), conferenceId)) {
                    participationService.add(s, conferenceId);
                }
            }
        }catch (MessagingException | IllegalArgumentException ex) {

            return ResponseEntity.badRequest().body(ex.getMessage());

        }

        return ResponseEntity.ok("Speakers have been invited");
    }

    @GetMapping("confirmInvitation/{hash}")
    public ResponseEntity submiteInvitation(@PathVariable("hash") Long hash) {

        if(!inviteService.isInvited(hash)) {
            return ResponseEntity.status(404).body("Invitation hasn't been found for this user.");
        }

        if(invitationConfirmationService.isInvitationAccepted(hash)) {
            return ResponseEntity.status(403).body("You has already accepted invitation.");
        }

        try {
            invitationConfirmationService.acceptInvitation(hash);
        }catch (MessagingException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }

        return ResponseEntity.ok("Speaker has been confirmed");
    }

}
