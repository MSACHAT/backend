package MSACHAT.backend.repository;

import MSACHAT.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findUserEntityById(Integer userId);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmailOrUsername(String email, String username);

    @Query(value = "SELECT username FROM UserEntity WHERE id =:id")
    String findNameById(@Param("id") Integer id);

    @Query(value = "SELECT id FROM UserEntity WHERE email =:emailOrUsername OR username =:emailOrUsername")
    Integer findUserIdByEmailOrByUsername(@Param("emailOrUsername") String emailOrUsername );
}