package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifCommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotifCommentRepository extends CrudRepository<NotifCommentEntity, Integer>,
        PagingAndSortingRepository<NotifCommentEntity, Integer> {
    Page<NotifCommentEntity> findAllByReceiverId(Integer receiverId, PageRequest pageRequest);
}
