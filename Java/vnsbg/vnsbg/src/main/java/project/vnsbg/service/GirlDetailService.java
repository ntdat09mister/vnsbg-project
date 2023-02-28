package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.GirlDetail;
import project.vnsbg.entity.GirlDetailImgs;
import project.vnsbg.entity.dto.GirlDetailDto;
import project.vnsbg.repository.GirlDetailImgsRepository;
import project.vnsbg.repository.GirlDetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GirlDetailService {

    private final GirlDetailImgsRepository girlDetailImgsRepository;

    private final GirlDetailRepository girlDetailRepository;

    public List<GirlDetailImgs> findGirlDetailImgsByGirlId(String girlId) {
        return girlDetailImgsRepository.findGirlDetailImgsProcedure(girlId);
    }

    public GirlDetail getGirlDetails(String girlId) {
        return girlDetailRepository.findByGirlId(girlId);
    }

    public List<GirlDetailDto> fillterGirlDetails(Long pageNumber, Long pageSize, Long sortBy, Long sortDirection,
                                                  Long marialStatus, String nationality, Float heightStart, Float heightEnd,
                                                  Float weightStart, Float weightEnd, Float roundMs1Start, Float roundMs1End,
                                                  Float roundMs2Start, Float roundMs2End, Float roundMs3Start, Float roundMs3End) {
        return girlDetailRepository.fillterGirlDetails(pageNumber, pageSize, sortBy, sortDirection, marialStatus, nationality, heightStart,
                heightEnd, weightStart, weightEnd, roundMs1Start, roundMs1End, roundMs2Start, roundMs2End, roundMs3Start, roundMs3End);

    }
}
