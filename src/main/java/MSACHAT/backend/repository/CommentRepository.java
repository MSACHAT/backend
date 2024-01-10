package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.PostEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>,
        PagingAndSortingRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByPostId(Integer postId);

    void deleteAllByPostId(Integer postId);

    CommentEntity findAllCommentsByPostId(Integer postId, PageRequest pageRequest);

    Page<CommentEntity> findAllByPostIdOrderByTimeStampDesc(Integer postId, Pageable pageRequest);

    List<CommentEntity> findCommentEntitiesByPostIdOrderByTimeStampDesc(Integer postId);
}
