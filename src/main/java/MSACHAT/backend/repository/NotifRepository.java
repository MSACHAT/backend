package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NotifRepository extends CrudRepository<NotifEntity, Integer>,
        PagingAndSortingRepository<NotifEntity, Integer> {
    Page<NotifEntity> findAllByReceiverIdOrderByTimeStampDesc(Integer receiverId, PageRequest pageRequest);

    NotifEntity findNotifEntityById(Integer notifId);

    Integer countAllByReceiverIdAndTimeStampAfter(Integer receiverId, Date timeStamp);

    void deleteAllByPostId(Integer postId);

    void deleteAllBySenderIdAndPostIdAndCommentContent(Integer SenderId,Integer postId,String CommentContent);

    Integer countAllByReceiverId(Integer receiverId);
}

