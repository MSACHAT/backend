package MSACHAT.backend.repository;

import MSACHAT.backend.entity.ImageEntity;
import MSACHAT.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
}