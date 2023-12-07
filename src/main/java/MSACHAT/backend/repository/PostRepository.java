package MSACHAT.backend.repository;

import MSACHAT.backend.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    PostEntity findPostEntityById(Integer Id);
    void deletePostEntityById(Integer id);
}
