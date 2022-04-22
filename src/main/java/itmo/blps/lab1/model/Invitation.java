package itmo.blps.lab1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Invitation {

    @Id
    @Column(name = "participation_id")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "participation_id", referencedColumnName = "id")
    private Participation participation;

    private Long hash;

    public Invitation(Long hash, Participation participation) {
        this.hash = hash;
        this.participation = participation;
    }
}
