package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.PromoDetail;

import java.util.List;

@Repository
public interface PromoDetailsRepository extends JpaRepository<PromoDetail, Long> {
    @Query(value = "exec GetPromoDetails ", nativeQuery = true)
    List<PromoDetail> getPromoDetailList();
}
