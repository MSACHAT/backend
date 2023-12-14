package MSACHAT.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotifService {
    public List getNotifs(Integer receiverId);
}
