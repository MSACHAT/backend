package MSACHAT.repository;

import MSACHAT.entity.LikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity,Integer> {
List<LikeEntity> findAllByUserId(Integer userId);
LikeEntity findLikeEntityByUserIdAndPostId(Integer userId, Integer postId);
void deleteLikeEntityByUserIdAndPostId(Integer userId,Integer postId);
void deleteAllByPostId(Integer postId);
}
