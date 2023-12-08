package MSACHAT.backend.service.impl;

import MSACHAT.backend.repository.NotifCommentRepository;
import MSACHAT.backend.repository.NotifLikeRepository;
import MSACHAT.backend.service.NotifService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifServiceImpl implements NotifService {
    NotifLikeRepository notifLikeRepository;
    NotifCommentRepository notifCommentRepository;

    public NotifServiceImpl(
            NotifLikeRepository notifLikeRepository,
            NotifCommentRepository notifCommentRepository
    ) {
        this.notifCommentRepository = notifCommentRepository;
        this.notifLikeRepository = notifLikeRepository;
    }

    public List getNotifs(Integer receiverId){
        List notifs=new ArrayList<>();
        notifs.addAll(notifCommentRepository.findAllByReceiverId(receiverId));
        notifs.addAll(notifLikeRepository.findAllByReceiverId(receiverId));
        return notifs;
    }
}
