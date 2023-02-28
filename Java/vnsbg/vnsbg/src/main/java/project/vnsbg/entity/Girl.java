package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "girls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Girl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String price;
    private String url;
}
