package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.Girl;
import project.vnsbg.repository.GirlRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GirlService {

    private final GirlRepository girlRepository;

    public List<Girl> getAllGirl() {
        return girlRepository.getAllBabies();
    }
}
