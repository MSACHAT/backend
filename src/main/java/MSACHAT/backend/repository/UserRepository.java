package MSACHAT.backend.repository;

import MSACHAT.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
    @Query(value = "SELECT id FROM UserEntity WHERE email =:emailOrUsername OR username =:emailOrUsername LIMIT 1")
    Integer findUserIdByEmailOrByUsername(@Param("emailOrUsername") String emailOrUsername );
}