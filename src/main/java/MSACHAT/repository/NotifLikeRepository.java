package MSACHAT.repository;

import MSACHAT.entity.NotifLikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotifLikeRepository extends CrudRepository<NotifLikeEntity,Integer> {
    ArrayList<NotifLikeEntity> findAllByReceiverId(Integer receiverId);
}