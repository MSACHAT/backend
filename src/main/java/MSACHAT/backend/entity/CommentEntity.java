package MSACHAT.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import java.sql.Date;


>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
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
    private Date datetime;


>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
}
