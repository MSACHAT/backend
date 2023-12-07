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
public class NotifCommentDto {
    private Integer id;
    private Integer postId;
    private Integer senderId;
    private Integer receiverId;
    private Date time;
    private boolean isRead;
}
