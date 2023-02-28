package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "girl_id")
    private String girlId;
    private String price;
    private String status;
    @Column(name = "vote_customer")
    private Float voteCustomer;
    @Column(name = "sales_date")
    private Date salesDate;
}
