package itmo.blps.lab1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Timestamp start;
    private Timestamp finish;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;


    public Conference(String name, String description, Timestamp start, Timestamp finish, Place place){
        this.name=name;
        this.description=description;
        this.start=start;
        this.finish=finish;
        this.place=place;
    }


}
