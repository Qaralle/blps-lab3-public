package itmo.blps.lab1.dto;

import itmo.blps.lab1.model.Speaker;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpeakersDTO {
    public Long conferenceId;
    public List<Long> speakerIdList = new ArrayList<>();
}
