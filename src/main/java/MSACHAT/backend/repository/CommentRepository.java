package MSACHAT.backend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.PostEntity;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer>,
        PagingAndSortingRepository<CommentEntity, Integer> {

    List<CommentEntity> findAllByPostId(Integer postId);

    void deleteAllByPostId(Integer postId);

    CommentEntity findAllCommentsByPostId(Integer postId, PageRequest pageRequest);

    CommentEntity findCommentEntityById(Integer commentId);

    void deleteById(Integer Id);
}
