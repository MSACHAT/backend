package MSACHAT.backend.repository;

<<<<<<< HEAD
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

=======
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import MSACHAT.backend.entity.CommentEntity;

import java.util.List;

>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByPostId(Integer postId);

    void deleteAllByPostId(Integer postId);

    CommentEntity findAllCommentsByPostId(Integer postId, PageRequest pageRequest);

}
