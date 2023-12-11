package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifLikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotifLikeRepository extends CrudRepository<NotifLikeEntity,Integer>,
        PagingAndSortingRepository<NotifLikeEntity,Integer> {
    Page<NotifLikeEntity> findAllByReceiverId(Integer receiverId, Pageable pageable);
}
