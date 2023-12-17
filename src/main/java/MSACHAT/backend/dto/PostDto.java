package MSACHAT.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {


    private String title;
    private String content;
    private List<String> image;
<<<<<<< HEAD
    private Date time;
    private Integer likeCount;
    private Integer commentCount;
    private boolean isLiked;
=======

>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
}
