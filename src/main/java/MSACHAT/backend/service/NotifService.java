package MSACHAT.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotifService {
    public List getNotifsByPageNum(Integer receiverId, Integer pageNum,Integer pageSize);
}
