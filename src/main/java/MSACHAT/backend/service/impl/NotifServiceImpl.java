package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.repository.NotifRepository;
import MSACHAT.backend.repository.PostRepository;
import MSACHAT.backend.repository.UserRepository;
import MSACHAT.backend.service.NotifService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotifServiceImpl implements NotifService {
    NotifRepository notifRepository;
    UserRepository userRepository;
    PostRepository postRepository;
    public NotifServiceImpl(
            NotifRepository notifRepository,
            UserRepository userRepository,
            PostRepository postRepository
    ) {
        this.notifRepository = notifRepository;
        this.userRepository=userRepository;
        this.postRepository=postRepository;
    }

    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<NotifEntity> notifPage = notifRepository.findAllByReceiverIdOrderByTimeStampDesc(receiverId,pageRequest);
        List<NotifEntity> notifs =
                new ArrayList<>(notifPage.getContent());
        for(int i=0;i<notifs.size();i++){
            NotifEntity notifDetail=notifs.get(i);
            notifDetail.setUserName(userRepository.findNameById(notifDetail.getSenderId()));
            Integer postId=notifDetail.getPostId();
            PostEntity post=postRepository.findPostEntityById(postId);
            if(!post.getImages().isEmpty()){
                notifDetail.setPreviewType("image");
                notifDetail.setPreviewString(post.getImages().get(0).getImageUrl());
            } else if (post.getTitle()!=null) {
                notifDetail.setPreviewType("text");
                notifDetail.setPreviewString(post.getTitle());
            }else{
                notifDetail.setPreviewType("text");
                notifDetail.setPreviewString(post.getContent());
            }
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
    public void isRead(NotifEntity notif) {
            notifRepository.updateIsReadForOlderThan(notif.getTimeStamp());
    }

    @Override
    public Long countNotifNums(){
        return notifRepository.count();
    }

    @Override
    public NotifEntity getNotifById(Integer notifId) {
        return notifRepository.findNotifEntityById(notifId);
    }
}
