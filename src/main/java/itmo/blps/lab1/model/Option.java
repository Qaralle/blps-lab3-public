package itmo.blps.lab1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
//    private Boolean value;
    
    @ManyToMany(mappedBy = "options")
    private List<Profile> profiles;

    public Option(String description, List<Profile> profiles){
        this.description=description;
//        this.value=value;
        this.profiles = profiles;
    }
    public void subscribe(Profile profile) {
        profiles.add(profile);
    }
}
