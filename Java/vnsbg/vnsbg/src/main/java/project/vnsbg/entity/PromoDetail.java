package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "promo_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "promo_detail")
    private String promoDetail;
}
