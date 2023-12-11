package MSACHAT.backend.repository;

import MSACHAT.backend.entity.PostEntity;
import jakarta.persistence.Transient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    PostEntity findPostEntityById(Integer Id);
    void deletePostEntityById(Integer id);

    @Modifying
    @Query(value = "UPDATE PostEntity SET likeCount = likeCount + 1 WHERE id = :postId")
    void addLikecount(Integer postId);
}
