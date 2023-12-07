package MSACHAT.backend.repository;

<<<<<<< HEAD:src/main/java/MSACHAT/repository/PostRepository.java
import MSACHAT.entity.PostEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
=======
import MSACHAT.backend.entity.PostEntity;
>>>>>>> be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
<<<<<<< HEAD:src/main/java/MSACHAT/repository/PostRepository.java

    @Query("SELECT p FROM PostEntity p LEFT JOIN FETCH p.comments")
    List<PostEntity> findAllWithComments();
=======
    PostEntity findPostEntityById(Integer Id);
    void deletePostEntityById(Integer id);
>>>>>>> be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java
}
