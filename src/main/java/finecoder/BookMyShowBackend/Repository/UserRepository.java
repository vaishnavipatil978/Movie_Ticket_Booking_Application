package finecoder.BookMyShowBackend.Repository;

import finecoder.BookMyShowBackend.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from user_table WHERE mobileNo=:UsermobileNo", nativeQuery = true)
    public UserEntity findUserByMobileNo(String UsermobileNo);

    @Query(value = "SELECT * FROM user_table WHERE name=:Username",nativeQuery = true)
    public List<UserEntity> findUserByName(String Username);
}
