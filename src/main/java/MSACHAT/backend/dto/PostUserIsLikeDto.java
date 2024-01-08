package MSACHAT.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import MSACHAT.backend.entity.PostEntity;

@Getter
@Setter
@NoArgsConstructor
public class PostUserIsLikeDto {
    private PostEntity postEntity;
    private Boolean isLiked;
    public Object getPostEntity;

    public PostUserIsLikeDto(PostEntity postEntity, Boolean isLiked) {
        this.postEntity = postEntity;
        this.isLiked = isLiked;
    }

    public Integer getId() {
        return null;
    }

}