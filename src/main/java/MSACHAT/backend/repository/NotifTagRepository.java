package MSACHAT.backend.repository;

import MSACHAT.backend.entity.NotifTagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifTagRepository extends CrudRepository<NotifTagEntity,Integer> {
    public NotifTagEntity findNotifTagEntityByUserId(Integer userId);
}
