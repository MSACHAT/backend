package MSACHAT.backend.service.impl;

import MSACHAT.backend.entity.NotifEntity;
import MSACHAT.backend.entity.NotifTagEntity;
import MSACHAT.backend.entity.PostEntity;
import MSACHAT.backend.entity.UserEntity;
import MSACHAT.backend.repository.*;
import MSACHAT.backend.service.NotifService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotifServiceImpl implements NotifService {
    NotifRepository notifRepository;
    UserRepository userRepository;
    PostRepository postRepository;
    NotifTagRepository notifTagRepository;
    public NotifServiceImpl(
            NotifRepository notifRepository,
            UserRepository userRepository,
            PostRepository postRepository,
            NotifTagRepository notifTagRepository
    ) {
        this.notifRepository = notifRepository;
        this.userRepository=userRepository;
        this.postRepository=postRepository;
        this.notifTagRepository=notifTagRepository;
    }

    public List<NotifEntity> getNotifsByPageNum(Integer receiverId, Integer pageNum,Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<NotifEntity> notifPage = notifRepository.findAllByReceiverIdOrderByTimeStampDesc(receiverId,pageRequest);
        List<NotifEntity> notifs =
                new ArrayList<>(notifPage.getContent());
        for(int i=0;i<notifs.size();i++){
            NotifEntity notifDetail=notifs.get(i);
            notifDetail.setUserName(userRepository.findNameById(notifDetail.getSenderId()));
            UserEntity user=userRepository.findUserEntityById(notifDetail.getSenderId());
            notifDetail.setUserAvatar(user.getAvatar());
            Integer postId=notifDetail.getPostId();
            PostEntity post=postRepository.findPostEntityById(postId);
            if(!post.getImages().isEmpty()){
                notifDetail.setPreviewType("image");
                notifDetail.setPreviewString(post.getImages().get(0).getImageUrl());
            } else{
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
    public Integer countNotifNumsByReceiverId(Integer receiverId){
        return notifRepository.countAllByReceiverId(receiverId);
    }

    @Override
    public NotifEntity getNotifById(Integer notifId) {
        return notifRepository.findNotifEntityById(notifId);
    }

    @Override
    public void setNotifTag(Date time,Integer userId) {
        NotifTagEntity notifTag=notifTagRepository.findNotifTagEntityByUserId(userId);
        if(time.after(notifTag.getTimeStamp())) {
            notifTag.setTimeStamp(time);
            notifTagRepository.save(notifTag);
        }
    }

    @Override
    public void newNotifTag(Date time,Integer userId) {
        NotifTagEntity notifTag=new NotifTagEntity();
        notifTag.setTimeStamp(time);
        notifTag.setUserId(userId);
        notifTagRepository.save(notifTag);
    }

    @Override
    public NotifTagEntity findNotifTagByUserId(Integer userId) {
        return notifTagRepository.findNotifTagEntityByUserId(userId);
    }

    @Override
    public Integer countNewNotifs(Integer receiverId, Date timeStamp) {
        return notifRepository.countAllByReceiverIdAndTimeStampAfter(receiverId,timeStamp);
    }

    @Override
    public void addNewNotif(Integer senderId, Integer receiverId, Integer postId,String commentContent) {
        NotifEntity notifEntity=new NotifEntity();
        notifEntity.setPostId(postId);
        notifEntity.setSenderId(senderId);
        notifEntity.setReceiverId(receiverId);
        if(commentContent != null){
            notifEntity.setCommentContent(commentContent);
        }
        notifEntity.setTimeStamp(new Date(System.currentTimeMillis()));
        notifRepository.save(notifEntity);
    }


}
