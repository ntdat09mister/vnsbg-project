package project.vnsbg.entity.dto;

import lombok.Data;

import java.util.Date;
@Data
public class GirlAddToCart {
    private String customerId;
    private String girlId;
    private String price;
    private Date salesDate;
    private String status;
}
