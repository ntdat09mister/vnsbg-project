package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.vnsbg.entity.dto.CartDto;
import project.vnsbg.repository.KpiRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KpiService {
    private final KpiRepository kpiRepository;
    @Transactional(readOnly = true)
    public List<CartDto> listingCart(Long customerId) {
        return kpiRepository.listingCart(customerId);
    }

    public void addToCart(String customerId, String girlId, String price) throws Exception {
        String salesDateCustom = "2023-02-03";
        String status = "0";
        if(customerId == null || customerId.isEmpty() || customerId.equalsIgnoreCase("null")) {
            throw new Exception("customerId null or empty");
        }
        kpiRepository.addToCart(customerId, girlId, price, salesDateCustom, status);
    }

    public void removeCartItem(String cartItemId) {
        kpiRepository.removeCartItem(cartItemId);
    }
}
