package MSACHAT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithCommentsDto {
    private Integer postId;
    private String title;
    private String content;
    private LocalDateTime postTime;
    private List<CommentDto> comments;


    @Override
    public String toString() {
        return "PostWithCommentsDto{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", comments=" + comments +
                '}';
    }

    // 内部类表示评论信息
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDto {
        private Integer commentId;
        private Integer userId;
        private String comment;
        private LocalDateTime commentTime;


        @Override
        public String toString() {
            return "CommentDto{" +
                    "commentId=" + commentId +
                    ", userId=" + userId +
                    ", comment='" + comment + '\'' +
                    ", commentTime=" + commentTime +
                    '}';
        }
    }
}
