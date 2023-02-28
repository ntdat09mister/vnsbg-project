package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "girl_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirlDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "girl_id")
    private String girlId;
    @Column(name = "marial_status")
    private Long marialStatus;
    private Long nationality;
    private Float height;
    private Float weight;
    @Column(name = "round_ms_1")
    private Float roundMs1;
    @Column(name = "round_ms_2")
    private Float roundMs2;
    @Column(name = "round_ms_3")
    private Float roundMs3;
    @Column(name = "rating_vote")
    private Float ratingVote;
}
