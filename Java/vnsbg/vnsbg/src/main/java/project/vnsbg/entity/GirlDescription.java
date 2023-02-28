package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "girl_description")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirlDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "girl_id")
    private String girlId;
    private String description;
}
