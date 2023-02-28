package project.vnsbg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.vnsbg.entity.User;
import project.vnsbg.entity.dto.CustomerIdDto;
import project.vnsbg.entity.dto.UserDetailDto;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "exec LoginProc @username = :userName, @password = :password", nativeQuery = true)
    List<CustomerIdDto> findByUsernameAndPassword(String userName, String password);

    @Query(value = "exec GetUserDetail @userId = :userId", nativeQuery = true)
    List<UserDetailDto> getUserDetail(String userId);
}
