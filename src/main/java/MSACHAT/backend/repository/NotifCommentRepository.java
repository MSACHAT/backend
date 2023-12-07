package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifCommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotifCommentRepository extends CrudRepository<NotifCommentEntity,Integer> {
    ArrayList<NotifCommentEntity> findAllByReceiverId(Integer receiverId);
}