<<<<<<< HEAD
package MSACHAT.dto;

=======
package MSACHAT.backend.dto;

import java.sql.Date;
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private int userId;
    private int postId;
<<<<<<< HEAD
    private String comment;
    private LocalDateTime time;
=======
    private String content;
    private Date timeStamp;
>>>>>>> 5fa82f9c98206472df488a5c9638d98d42aafa32
}