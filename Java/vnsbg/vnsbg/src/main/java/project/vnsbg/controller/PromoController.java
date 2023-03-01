package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.vnsbg.entity.PromoDetail;
import project.vnsbg.service.PromoDetailService;

import java.util.List;

@RestController
public class PromoController {
    @Autowired
    PromoDetailService promoDetailService;
    @GetMapping("promoDetail")
    public List<PromoDetail> getPromoDetailList() {
        return promoDetailService.getPromoDetailList();
    }
}
