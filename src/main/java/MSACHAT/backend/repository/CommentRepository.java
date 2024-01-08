package MSACHAT.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import MSACHAT.backend.entity.CommentEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByPostId(Integer postId);

    void deleteAllByPostId(Integer postId);

    Page<CommentEntity> findAllByPostId(Integer postId, Pageable pageable);

    CommentEntity findAllCommentsByPostId(Integer postId, PageRequest pageRequest);

}
