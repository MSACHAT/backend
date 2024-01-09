package MSACHAT.backend.repository;

import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostUserIsLikeDto;
import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.PostEntity;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    PostEntity findPostEntityById(Integer Id);

    // @Query("SELECT new MSACHAT.backend.dto.PostUserIsLikeDto(p, CASE WHEN l.id IS
    // NOT NULL THEN true ELSE false END) " +
    // "FROM PostEntity p LEFT JOIN LikeEntity l ON p.id = l.postId AND l.userId =
    // :userId")
    // List<PostUserIsLikeDto> findAllWithLikeStatus(@Param("userId") Integer
    // userId, Pageable pageable);

    // List<Object[]> findAllWithLikeStatus(Pageable pageable, Integer userId);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    @Modifying
    @Query(value = "UPDATE PostEntity SET likeCount = likeCount + 1 WHERE id = :postId")
    void addLikesCount(@Param("postId") Integer postId);

}
