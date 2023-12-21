package MSACHAT.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReturnDto {
    Integer id;
    String userName;

    String content;
    List<String> images;
    Date timeStamp;
    Integer likeCount;
    Integer commentCount;
    Boolean isLiked;
}
