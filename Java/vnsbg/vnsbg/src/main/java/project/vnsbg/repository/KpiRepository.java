package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.Cart;
import project.vnsbg.entity.dto.CartDto;

import java.util.List;

@Repository
public interface KpiRepository extends JpaRepository<Cart, Long> {
    @Procedure("ListCart")
    List<CartDto> listingCart(@Param("customer_id") Long customerId);
    @Procedure("AddToCart")
    void addToCart(@Param("customerId") String customerId, @Param("girlId") String girlId, @Param("price") String price,
                   @Param("saleDate") String salesDateCustom, @Param("status") String status);
    @Procedure("RemoveCartItem")
    void removeCartItem(@Param("cartItemId") String cartItemId);
}
