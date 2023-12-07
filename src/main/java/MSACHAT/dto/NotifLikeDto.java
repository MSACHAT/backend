package MSACHAT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotifLikeDto {
    private Integer id;
    private Integer postId;
    private String commentContent;
    private Date time;
    private Integer senderId;
    private Integer receiverId;
    private boolean isRead;
}
