package MSACHAT.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
@Data
=======
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String userName;
<<<<<<< HEAD
    private String title;
    private String content;
<<<<<<< HEAD
    private String image;
<<<<<<< HEAD
    private Date time;
    private int commentcount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

=======
=======
=======
>>>>>>> a37652f6c2b5e42127bf363b1c448a7a195a1f20

    private String content;
    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    private List<ImageEntity> images;

>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
    private Date timeStamp;
    private Integer likeCount;
    private Integer commentCount;
    @Transient
    private boolean isLiked;
>>>>>>> ae838c8d1ff94a9add56e2c0e50f44aafbcc10fe
}
