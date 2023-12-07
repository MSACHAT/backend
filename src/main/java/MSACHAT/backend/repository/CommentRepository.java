package MSACHAT.backend.repository;

import MSACHAT.backend.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer> {
    void deleteAllByPostId(Integer postId);
}
