package MSACHAT.backend.repository;

import MSACHAT.backend.entity.CommentEntity;
import MSACHAT.backend.entity.PostEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer>,
        PagingAndSortingRepository<PostEntity, Integer> {
    PostEntity findPostEntityById(Integer Id);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    @Modifying
    @Query(value = "UPDATE PostEntity SET likeCount = likeCount + 1 WHERE id = :postId")
    void addLikesCount(@Param("postId") Integer postId);

}
