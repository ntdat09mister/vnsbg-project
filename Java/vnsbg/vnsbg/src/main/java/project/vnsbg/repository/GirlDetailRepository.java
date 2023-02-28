package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.GirlDetail;
import project.vnsbg.entity.dto.GirlDetailDto;

import java.util.List;

@Repository
public interface GirlDetailRepository extends JpaRepository<GirlDetail, Long> {
    @Query(value = "exec findGirlDetailByGirlId @girlId = :girlId", nativeQuery = true)
    GirlDetail findByGirlId(String girlId);

    @Query(value = "exec fillterGirlDetail @PageNumber = ?1, @PageSize = ?2, @SortBy = ?3, @SortDirection = ?4, @marial_status = ?5, " +
            "@nationality = ?6, @height_start = ?7, @height_end = ?8, @weight_start = ?9, @weight_end = ?10, @round_ms1_start = ?11, " +
            "@round_ms1_end = ?12, @round_ms2_start = ?13, @round_ms2_end = ?14, @round_ms3_start = ?15, @round_ms3_end = ?16", nativeQuery = true)
    List<GirlDetailDto> fillterGirlDetails(Long pageNumber, Long pageSize, Long sortBy, Long sortDirection, Long marialStatus,
                                           String nationality, Float heightStart, Float heightEnd, Float weightStart,
                                           Float weightEnd, Float roundMs1Start, Float roundMs1End, Float roundMs2Start,
                                           Float roundMs2End, Float roundMs3Start, Float roundMs3End);
}
