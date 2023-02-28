package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.dto.GirlDtoHomePage;
import project.vnsbg.repository.GirlRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GirlService {

    private final GirlRepository girlRepository;

    public List<GirlDtoHomePage> getAllGirl() {
        return girlRepository.getAllBabies();
    }

    public List<GirlDtoHomePage> getTopGirlReaction() {
        return girlRepository.getTopGirlReaction();
    }
}
