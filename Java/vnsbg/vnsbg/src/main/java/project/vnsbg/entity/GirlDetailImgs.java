package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "girl_detail_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirlDetailImgs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "girl_id")
    private String girlId;
    private String url;
}
