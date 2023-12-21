package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.repository.NotifRepository;
import MSACHAT.backend.repository.UserRepository;
import MSACHAT.backend.service.NotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifServiceImpl implements NotifService {
    NotifRepository notifRepository;
    UserRepository userRepository;

    public NotifServiceImpl(
            NotifRepository notifRepository,
            UserRepository userRepository
    ) {
        this.notifRepository = notifRepository;
        this.userRepository=userRepository;
    }

    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<NotifEntity> notifPage = notifRepository.findAllByReceiverIdOrderByTimeStamp(receiverId, pageRequest);
        List<NotifEntity> notifs =
                new ArrayList<>(notifPage.getContent());
        for(int i=0;i<notifs.size();i++){
            NotifEntity notifDetail=notifs.get(i);
            notifDetail.setUserName(userRepository.findNameById(notifDetail.getSenderId()));
            notifs.set(i,notifDetail);
        }
        return notifs;
    }

    @Override
    public Integer countTotalPagesByPageSize(Integer pageSize){
        double pageCount=notifRepository.count()/(pageSize*1.0);
        return (int)Math.ceil(pageCount)-1;
    }

    @Override
    public void isRead(List<NotifEntity> notifs) {
        for (NotifEntity notif: notifs){
            notif.setRead(true);
            notifRepository.save(notif);
        }
    }
}
