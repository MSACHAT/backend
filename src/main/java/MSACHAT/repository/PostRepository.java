package MSACHAT.repository;

import MSACHAT.entity.PostEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {

    @Query("SELECT p FROM PostEntity p LEFT JOIN FETCH p.comments")
    List<PostEntity> findAllWithComments();
}
