package project.vnsbg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "username")
    private String userName;
    private String password;
    @Column(name = "full_name")
    private String fullName;
}
