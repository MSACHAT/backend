package MSACHAT.backend.repository;

import MSACHAT.backend.entity.CommentEntity;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer> {
    void deleteAllByPostId(Integer postId);


}
