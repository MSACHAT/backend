package MSACHAT.backend.service;

import MSACHAT.backend.entity.NotifEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotifService {
    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum, Integer pageSize);

    public Integer countTotalPagesByPageSize(Integer pageSize);
}
