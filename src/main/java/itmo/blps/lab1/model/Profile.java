package itmo.blps.lab1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_options",
    joinColumns = @JoinColumn(name = "profile_id"),
    inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> options = new ArrayList<>();

    public Profile(String name, Double price, List<Option> options){
        this.name=name;
        this.price=price;
        this.options=options;
    }


}
