package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.NotifCommentEntity;
import MSACHAT.backend.entity.NotifLikeEntity;
import MSACHAT.backend.repository.NotifCommentRepository;
import MSACHAT.backend.repository.NotifLikeRepository;
import MSACHAT.backend.service.NotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List getNotifsByPageNum(Integer receiverId, Integer pageNum,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<NotifLikeEntity> notifLikeEntityPage = notifLikeRepository.findAllByReceiverId(receiverId, pageRequest);
        Page<NotifCommentEntity> notifCommentEntityPage = notifCommentRepository.findAllByReceiverId(receiverId, pageRequest);
        List notifs = new ArrayList<>();
        notifs.addAll(notifCommentEntityPage.getContent());
        notifs.addAll(notifLikeEntityPage.getContent());
        return notifs;
    }
}
