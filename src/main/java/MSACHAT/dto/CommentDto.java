package MSACHAT.dto;

import java.time.LocalDateTime;

public public static class CommentDto {
    private int id;
    private int userId;
    private int postId;
    private String comment;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }

    {
    
}
