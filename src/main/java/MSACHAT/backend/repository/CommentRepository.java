package MSACHAT.repository;

import MSACHAT.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByPostId(Integer postId);

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer> {
    void deleteAllByPostId(Integer postId);
}
