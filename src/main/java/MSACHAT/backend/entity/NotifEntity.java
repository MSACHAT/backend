package MSACHAT.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifs")
public class NotifEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer postId;
    private Integer senderId;
    private Integer receiverId;
    private Date timeStamp;
    private boolean isRead;
    private String commentContent;
    @Transient
    private String userName;
    @Transient
    private String previewType;
    @Transient
    private String previewString;
}
