package MSACHAT.backend.repository;

import MSACHAT.backend.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);

    Set<RoleEntity> findRoleEntitiesByName(String name);
}
