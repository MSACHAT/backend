package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface NotifRepository extends CrudRepository<NotifEntity, Integer>,
        PagingAndSortingRepository<NotifEntity, Integer> {
    Page<NotifEntity> findAllByReceiverIdOrderByTimeStampDesc(Integer receiverId, PageRequest pageRequest);
    @Modifying
    @Transactional
    @Query("UPDATE NotifEntity y SET y.isRead = true WHERE y.timeStamp <= :timeStamp")
    void updateIsReadForOlderThan(Date timeStamp);

    NotifEntity findNotifEntityById(Integer notifId);
}

