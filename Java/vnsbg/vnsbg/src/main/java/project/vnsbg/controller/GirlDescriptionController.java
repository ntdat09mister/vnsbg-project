package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.vnsbg.entity.GirlDescription;
import project.vnsbg.service.GirlDescriptionService;

@RestController
public class GirlDescriptionController {
    @Autowired
    private GirlDescriptionService girlDescriptionService;

    @GetMapping("detailDescription")
    public GirlDescription findGirlDescriptionByGirlId(@RequestParam String girlId) {
        return girlDescriptionService.findGirlDescriptionByGirlId(girlId);
    }

}
