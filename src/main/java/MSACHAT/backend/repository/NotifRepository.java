package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifRepository extends CrudRepository<NotifEntity, Integer>,
        PagingAndSortingRepository<NotifEntity, Integer> {
    Page<NotifEntity> findAllByReceiverIdOrderByTimeStamp(Integer receiverId, PageRequest pageRequest);
}
