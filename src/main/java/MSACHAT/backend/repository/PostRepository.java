package MSACHAT.backend.repository;

<<<<<<< HEAD:src/main/java/MSACHAT/repository/PostRepository.java
import MSACHAT.entity.PostEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
=======
import MSACHAT.backend.entity.PostEntity;
<<<<<<< HEAD
>>>>>>> be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
<<<<<<< HEAD:src/main/java/MSACHAT/repository/PostRepository.java

    @Query("SELECT p FROM PostEntity p LEFT JOIN FETCH p.comments")
    List<PostEntity> findAllWithComments();
=======
    PostEntity findPostEntityById(Integer Id);
    void deletePostEntityById(Integer id);
>>>>>>> be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java
=======
public interface PostRepository extends CrudRepository<PostEntity, Integer>,
        PagingAndSortingRepository<PostEntity, Integer> {
    PostEntity findPostEntityById(Integer Id);

    void deleteById(Integer id);
    boolean existsById(Integer id);

    @Modifying
    @Query(value = "UPDATE PostEntity SET likeCount = likeCount + 1 WHERE id = :postId")
<<<<<<< HEAD
    void addLikesCount(Integer postId);
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
=======
    void addLikesCount(@Param("postId") Integer postId);
<<<<<<< HEAD

>>>>>>> 726cd29aca422765ef93c1fdf257065f18ba4fe2
=======
>>>>>>> a37652f6c2b5e42127bf363b1c448a7a195a1f20
}
