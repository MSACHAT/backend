package MSACHAT.backend.service;

import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.entity.NotifTagEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public interface NotifService {
    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum, Integer pageSize);

    public Integer countTotalPagesByPageSize(Integer pageSize);

    public Integer countNotifNumsByReceiverId(Integer ReceiverId);

    public NotifEntity getNotifById(Integer notifId);

    public void setNotifTag(Date time, Integer userId);

    void newNotifTag(Date time, Integer userId);

    public NotifTagEntity findNotifTagByUserId(Integer userId);

    public Integer countNewNotifs(Integer receiverId, Date timeStamp);

    public void addNewNotif(Integer senderId, Integer receiverId,Integer postId,String commentContent);

}
