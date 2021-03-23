package uz.pdp.RestFullApiTwo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "language_id"}),
        @UniqueConstraint(columnNames = {"text", "language_id"})})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean isDone;

    private boolean hasStar;

    @Column(nullable = false)
    private String text;

    private String help;

    @ManyToOne(optional = false)
    private Language language;

}
