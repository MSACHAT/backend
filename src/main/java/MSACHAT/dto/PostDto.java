package MSACHAT.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private List<String> images;
    private LocalDateTime time;

    // 构造函数、getter和setter等根据需要添加

    public PostDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public PostDto(int id, int userId, String title, String content, List<String> images, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.images = images;
        this.time = time;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", time=" + time +
                '}';
    }
}
