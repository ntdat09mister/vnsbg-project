package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.vnsbg.entity.GirlDetail;
import project.vnsbg.entity.GirlDetailImgs;
import project.vnsbg.entity.dto.BabyDetailDto;
import project.vnsbg.entity.dto.GirlDetailDto;
import project.vnsbg.service.GirlDetailService;

import java.util.List;

@RestController
public class GirlDetailController {
    @Autowired
    private GirlDetailService girlDetailService;

    @GetMapping("detailImgs")
    public List<GirlDetailImgs> girlDetailImgsList(@RequestParam String girlId) {
        return girlDetailService.findGirlDetailImgsByGirlId(girlId);
    }

    @GetMapping("detail")
    public BabyDetailDto getGirlDetails(@RequestParam String girlId) {
        return girlDetailService.getGirlDetails(girlId);
    }

    @GetMapping("search")
    public List<GirlDetailDto> fillterGirlDetails(@RequestParam(required = false) Long pageNumber,
                                                  @RequestParam(required = false) Long pageSize,
                                                  @RequestParam(required = false) Long sortBy,
                                                  @RequestParam(required = false) Long sortDirection,
                                                  @RequestParam(required = false) Long marialStatus,
                                                  @RequestParam(required = false) String nationality,
                                                  @RequestParam(required = false) Float heightStart,
                                                  @RequestParam(required = false) Float heightEnd,
                                                  @RequestParam(required = false) Float weightStart,
                                                  @RequestParam(required = false) Float weightEnd,
                                                  @RequestParam(required = false) Float roundMs1Start,
                                                  @RequestParam(required = false) Float roundMs1End,
                                                  @RequestParam(required = false) Float roundMs2Start,
                                                  @RequestParam(required = false) Float roundMs2End,
                                                  @RequestParam(required = false) Float roundMs3Start,
                                                  @RequestParam(required = false) Float roundMs3End
                                               ) {
        if (pageNumber == null) {
            pageNumber = 1L;
        }
        if (pageSize == null) {
            pageSize = 12L;
        }
        return girlDetailService.fillterGirlDetails(pageNumber, pageSize, sortBy, sortDirection, marialStatus, nationality, heightStart,
                heightEnd, weightStart, weightEnd, roundMs1Start, roundMs1End, roundMs2Start, roundMs2End, roundMs3Start, roundMs3End);
    }
}
