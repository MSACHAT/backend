package MSACHAT.backend.repository;

import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostUserIsLikeDto;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    PostEntity findPostEntityById(Integer Id);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    Page<PostEntity> findAllByUserId(Integer userId, Pageable pageable);

    Page<PostEntity> findByUserId(Integer userId, Pageable pageable);

    @Data
    @AllArgsConstructor
    public class PostResponse {
        private int totalPages;
        private int pageNum;
        private List<PostEntity> data;
    }

}
