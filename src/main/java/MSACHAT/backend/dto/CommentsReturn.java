package MSACHAT.backend.dto;

import MSACHAT.backend.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsReturn {
    List<CommentEntity> comments;
    Integer totalPages;
    Boolean hasMore;
}
