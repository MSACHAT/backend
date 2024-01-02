package MSACHAT.backend.service;

import MSACHAT.backend.entity.NotifEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface NotifService {
    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum, Integer pageSize);

    public Integer countTotalPagesByPageSize(Integer pageSize);

    public void isRead(NotifEntity notif);

    public Long countNotifNums();

    public NotifEntity getNotifById(Integer notifId);
}
