package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.GirlDetailImgs;

import java.util.List;

@Repository
public interface GirlDetailImgsRepository extends JpaRepository<GirlDetailImgs, Long> {
    @Query(value = "EXEC GetImagesBabyDetail @girlId = :girlId", nativeQuery = true)
    List<GirlDetailImgs> findGirlDetailImgsProcedure(String girlId);
}
