package MSACHAT.backend.repository;

import MSACHAT.backend.dto.PostDto;
import MSACHAT.backend.dto.PostUserIsLikeDto;
import MSACHAT.backend.entity.CommentEntity;<<<<<<<HEAD:src/main/java/MSACHAT/repository/PostRepository.java
import MSACHAT.entity.PostEntity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;=======
import MSACHAT.backend.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;<<<<<<<HEAD>>>>>>>be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    <<<<<<<HEAD
    public interface PostRepository extends CrudRepository<PostEntity, Integer> {
        <<<<<<<HEAD:src/main/java/MSACHAT/repository/PostRepository.java

        @Query("SELECT p FROM PostEntity p LEFT JOIN FETCH p.comments")
        List<PostEntity> findAllWithComments();=======

        PostEntity findPostEntityById(Integer Id);

        void deletePostEntityById(
                Integer id);>>>>>>>be8508c57155d34aa1dbbe12e1b3b81a9d546f73:src/main/java/MSACHAT/backend/repository/PostRepository.java=======

        public interface PostRepository extends CrudRepository<PostEntity, Integer>,
                PagingAndSortingRepository<PostEntity, Integer> {
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
<<<<<<< HEAD
    void addLikesCount(Integer postId);>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32=======

            void addLikesCount(@Param("postId") Integer postId);

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
