package MSACHAT.backend.entity;

<<<<<<<HEAD

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;<<<<<<<HEAD
import java.time.LocalDateTime;==============
import jakarta.persistence.*;>>>>>>>ac06df0c1f8b04ce94c65a3848c3a7b510029156

import java.util.Date;

>>>>>>>5f a82f9c98206472df488a5c9638d98d42aafa32
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
<<<<<<< HEAD

    private Integer userId; // 评论用户的ID
    private Integer postId; // 评论所属的帖子ID
    private String comment; // 评论内容
    private LocalDateTime time; // 评论时间

    @ManyToOne
    @JoinColumn(name = "postid")
    private PostEntity post; // 评论关联的帖子实体
=======
    private Integer userId;
    private Integer postId;
    private String content;
    private Date timeStamp;
    @Transient
    private String userAvatar;
    @Transient
    private String userName;
}
