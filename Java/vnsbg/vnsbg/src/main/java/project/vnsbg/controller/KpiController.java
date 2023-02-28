package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vnsbg.entity.dto.CartDto;
import project.vnsbg.service.KpiService;
import java.util.List;

@RestController
public class KpiController {
    @Autowired
    KpiService kpiService;
    @GetMapping("listCart")
    public List<CartDto> listingCart(@RequestParam Long customerId) {
        return kpiService.listingCart(customerId);
    }

    @PostMapping("addToCart")
    public void addToCart(@RequestParam String customerId,
                            @RequestParam String girlId,
                            @RequestParam String price) throws Exception {
        kpiService.addToCart(customerId, girlId, price);
    }

    @DeleteMapping("removeCartItem")
    public void removeCartItem(@RequestParam String cartItemId) {
        kpiService.removeCartItem(cartItemId);
    }

}
