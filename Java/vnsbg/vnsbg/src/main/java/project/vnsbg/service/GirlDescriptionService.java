package project.vnsbg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.vnsbg.entity.GirlDescription;
import project.vnsbg.repository.GirlDescriptionRepository;

@Service
@RequiredArgsConstructor
public class GirlDescriptionService {

    private final GirlDescriptionRepository girlDescriptionRepository;

    public GirlDescription findGirlDescriptionByGirlId(String girlId) {
        return girlDescriptionRepository.findGirlDescriptionByGirlId(girlId);
    }
}
