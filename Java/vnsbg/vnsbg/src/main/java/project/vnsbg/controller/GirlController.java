package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.vnsbg.entity.dto.GirlDtoHomePage;
import project.vnsbg.service.GirlService;

import java.util.List;

@RestController
public class GirlController {
    @Autowired
    private GirlService girlService;

    @GetMapping("girl")
    public List<GirlDtoHomePage> getAllGirl(){
        return girlService.getAllGirl();
    }

    @GetMapping("topGirlReact")
    public List<GirlDtoHomePage> getTopGirlReaction() {
        return girlService.getTopGirlReaction();
    }
}
