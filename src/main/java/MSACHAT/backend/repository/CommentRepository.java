package MSACHAT.backend.repository;

<<<<<<< HEAD
import MSACHAT.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
=======
import MSACHAT.backend.entity.CommentEntity;
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByPostId(Integer postId);

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer> {
    void deleteAllByPostId(Integer postId);
}
