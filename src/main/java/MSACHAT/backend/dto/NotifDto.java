package MSACHAT.backend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotifDto {
    private Integer id;
    private Integer postId;
    private Integer senderId;
    private Integer receiverId;
    private Date timeStamp;
    private boolean isRead;
    private String commentContent;
    private String userName;
}
