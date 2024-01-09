package MSACHAT.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.sql.Date;
=======
import java.util.Date;
import java.util.ArrayList;
>>>>>>> 7fec300a921853d8c57df5074633854c93a03bfc
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String userName;
    private String content;
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<ImageEntity> images;
    private Date timeStamp;
    private Integer likeCount;
    private Integer commentCount;
    @Transient
    private boolean isLiked;
}
