package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.GirlDescription;

@Repository
public interface GirlDescriptionRepository extends JpaRepository<GirlDescription, Long> {
    @Query(value = "EXEC GetGirlDesByGirlId @girlId = :girlId", nativeQuery = true)
    GirlDescription findGirlDescriptionByGirlId(String girlId);
}
