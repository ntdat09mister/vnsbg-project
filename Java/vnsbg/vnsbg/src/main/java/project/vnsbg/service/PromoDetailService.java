package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.PromoDetail;
import project.vnsbg.repository.PromoDetailsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromoDetailService {
    private final PromoDetailsRepository promoDetailsRepository;

    public List<PromoDetail> getPromoDetailList() {
        return promoDetailsRepository.getPromoDetailList();
    }

}
