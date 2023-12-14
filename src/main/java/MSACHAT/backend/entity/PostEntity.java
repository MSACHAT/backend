package MSACHAT.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String title;
    private String content;
    private String image;
<<<<<<< HEAD
    private Date time;
    private int commentcount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

=======
    private Date timeStamp;
    private Integer likeCount;
    private Integer commentCount;
    @Transient
    private boolean isLiked;
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
}
