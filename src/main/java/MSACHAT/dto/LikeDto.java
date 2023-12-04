package MSACHAT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private Date time;
}
