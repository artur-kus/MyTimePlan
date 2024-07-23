package top.arturkus.MyTimePlan.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.arturkus.MyTimePlan.helpers.StarHelper;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STAR")
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DISTANCE")
    private long distance;

    public Star(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }

    public Star(StarHelper star) {
        fillFields(star);
    }

    public void fillFields(StarHelper helper) {
        this.name = helper.getName();
        this.distance = helper.getDistance();
    }
}
