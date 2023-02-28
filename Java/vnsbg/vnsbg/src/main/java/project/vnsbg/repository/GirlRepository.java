package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.Girl;
import project.vnsbg.entity.dto.GirlDtoHomePage;

import java.util.List;

@Repository
public interface GirlRepository extends JpaRepository<Girl, Long> {
    @Query(value = "EXEC GetAllBabies", nativeQuery = true)
    List<GirlDtoHomePage> getAllBabies();

    @Query(value = "EXEC ListGirlTopReac", nativeQuery = true)
    List<GirlDtoHomePage> getTopGirlReaction();
}
