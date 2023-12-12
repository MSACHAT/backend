package MSACHAT.backend.repository;

import MSACHAT.backend.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntityRepository extends CrudRepository<ImageEntity, Integer> {
}